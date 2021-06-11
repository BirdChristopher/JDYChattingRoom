package center;

import Service.GroupService;
import Service.HistoryService;
import Service.UserService;
import entity.*;
import exception.MyException;
import java.util.Date;
import java.util.List;

/**
 * 服务器控制类
 * <p>包含了服务器处理各种请求的核心过程</p>
 * @author 季晓东
 */
public class ServerController {
    /**
     * 处理登录操作的核心方法
     * @param msg 客户端原始信息
     * @return 返回给与客户端的答复信息
     * @throws MyException 找不到对应用户或者密码错误
     */
    public static User login(String msg) throws MyException {
        String[] attrs = msg.split("#");
        String username = attrs[1];
        String password = attrs[2];
        synchronized (CenterServer.sqlSession){
            User name_user = new User();
            name_user.setUsername(username);
            List<User> returnUserList = UserService.searchUser(name_user);
            if(returnUserList.size()==0){
                //找不到对应用户
                throw new MyException(402);
            }
            else{
                User user = returnUserList.get(0);
                if(user.password.equals(password)) {
                    return user;
                }
                else{
                    //密码错误
                    throw new MyException(401);
                }
            }
        }
    }
    /**
     * 处理用户注册的核心方法
     * @param msg 客户端原始信息
     * @return 答复给客户端的信息
     * @throws MyException 用户名重复
     */
    public static String register(String msg) throws MyException{
        String[] attrs = msg.split("#");
        String username = attrs[1];
        String password = attrs[2];
        String sex = attrs[3];
        int avatar = Integer.parseInt(attrs[4]);
        String introduction = attrs[5];
        synchronized (CenterServer.sqlSession){
            User dup = new User();
            dup.setUsername(username);
            List<User> userList = UserService.searchUser(dup);
            if(userList.size()>0){
                throw new MyException(404);
            }
            else{
                User user = new User();
                user.setAvatar(avatar);
                user.setIntroduction(introduction);
                user.setSex(sex);
                user.setUsername(username);
                user.setPassword(password);
                return "register#200#"+UserService.addUser(user);
            }
        }
    }

    /**
     * 处理添加好友的核心方法
     * @param self 添加发起者 {@link entity.User}
     * @param msg 客户端原始信息
     * @throws MyException 已经添加过该好友
     */
    public static void addFriends(User self,String msg) throws MyException{
        String[] attrs = msg.split("#");
        int target_user_id = Integer.parseInt(attrs[1]);
        int self_id = self.getId();
        synchronized (CenterServer.sqlSession) {
            Friends friend = new Friends(target_user_id,self_id);
            List<Friends> friends_entry_list = HistoryService.findFriendRelation(friend);
            if (friends_entry_list.size()>0){
                throw new MyException(405);
            }
            HistoryService.buildFriendShip(friend);
            String result = "upFriend#"+self.id+"#"+self.getUsername()+"#"+self.avatar;
            CenterServer.sendToSpecificUser(target_user_id,result);
        }
    }

    /**
     * 处理创建群聊的核心方法
     * @param msg 客户端原信息
     * @return 答复内容
     * @throws MyException 用户id有错误
     */
    public static String createGroup(String msg) throws MyException{
        String[] attrs = msg.split("#");
        String group_name = attrs[1];
        int group_avatar = Integer.parseInt(attrs[2]);
        int i;

        synchronized (CenterServer.sqlSession) {
            Group new_group = new Group();
            new_group.setAvatar(group_avatar);
            new_group.setName(group_name);
            try{
                int group_id = GroupService.addGroup(new_group);
                userBelong2Group entry;
                User norm=new User();
                User member;
                //加入从属记录
                String result = "upGroup#"+new_group.getId()+"#"+new_group.getName()+"#"+new_group.getAvatar();
                for(i = 3;i<attrs.length;i++){
                    entry = new userBelong2Group();
                    entry.setGroup_id(group_id);
                    entry.setUser_id(Integer.parseInt(attrs[i]));
                    norm.setId(Integer.parseInt(attrs[i]));
                    member = UserService.searchUser(norm).get(0);
                    entry.setNickname(member.getUsername());
                    HistoryService.addMember2Group(entry);
                    if(i!=attrs.length-1){
                        CenterServer.sendToSpecificUser(Integer.parseInt(attrs[i]),result);
                    }
                }
                return "cgroup#200#"+group_id+"#"+group_name+"#"+group_avatar;
            }catch(IndexOutOfBoundsException e){
                throw new MyException(406);
            }

        }
    }

    /**
     * 处理加入群聊的核心方法
     * @param self 操作发起者 {@link entity.User}
     * @param msg 客户端原信息
     * @throws MyException 已经加入过该群聊或者群聊不存在
     */
    public static void joinGroup(User self,String msg) throws MyException{
        String[] attrs = msg.split("#");
        int group_id = Integer.parseInt(attrs[1]);
        synchronized (CenterServer.sqlSession){
            //查证是否已经加入过了
            userBelong2Group belong_entry = new userBelong2Group();
            belong_entry.setGroup_id(group_id);
            self.setBelongInfo(belong_entry);
            if(HistoryService.showBelongInfo(self).size()>0){
                throw new MyException(407);
            }

            belong_entry.setUser_id(self.getId());
            belong_entry.setNickname(self.getUsername());
            HistoryService.addMember2Group(belong_entry);
            //群发提醒消息
            Group group_form = new Group();
            group_form.setId(group_id);
            Group group;
            try{
                group = GroupService.findGroup(group_form).get(0);
            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();
                throw new MyException(500);
            }
            List<User> userList = HistoryService.showAllMembers(group);
            String result = "upGroupM#"+group_id+"#"+self.getId()+"#"+self.getUsername()+"#"+self.getAvatar();
            CenterServer.send2Group(userList,result);
        }
    }

    /**
     * 处理私聊信息的核心方法
     * @param msg 客户端原信息
     * @throws MyException 目标用户不存在
     */
    public static void privateTalk(String msg)throws MyException{
        String[] attrs = msg.split("#");
        int user_from_id = Integer.parseInt(attrs[1]);
        int user_to_id = Integer.parseInt(attrs[2]);
        String content = attrs[3];
        synchronized (CenterServer.sqlSession){
            User user = new User();
            user.setId(user_to_id);
            List<User> userList = UserService.searchUser(user);
            if(userList.size()==0){
                throw new MyException(500);
            }
            //推送消息
            User targetUser = userList.get(0);
            CenterServer.sendToSpecificUser(targetUser.getId(),"P#"+user_from_id+"#"+content+"#"+new Date());
            //存储记录
            PrivateMessage primsg = new PrivateMessage(user_from_id,user_to_id,content);
            HistoryService.addPrivateMessage(primsg);
        }
    }

    /**
     * 处理群聊信息的核心方法
     * @param user 操作发起者 {@link entity.User}
     * @param msg 客户端原信息
     * @throws MyException 发起者不属于该群
     */
    public static void groupTalk(User user,String msg) throws MyException{
        String[] attrs = msg.split("#");
        int group_id = Integer.parseInt(attrs[1]);
        String content = attrs[3];
        synchronized (CenterServer.sqlSession){
            //群聊成员身份信息获取，顺便取一波群聊专用昵称，虽然并没有什么用
            //userBelong2Group entry = new userBelong2Group();
            //entry.setGroup_id(group_id);
            user.setBelong_group_id(group_id);
            List<userBelong2Group> belong_entry = HistoryService.showBelongInfo(user);

            if(belong_entry.size()==0){
                throw new MyException(500);//此人不属于该群
            }
            else{
                //带上了备注名。。。随便了。
                GroupMessage newMsg = new GroupMessage(group_id,content,user.id,belong_entry.get(0).getNickname());
                HistoryService.addGroupMessage(newMsg);

                Group TargetGroup = new Group();
                TargetGroup.setId(group_id);
                List<User> userList = HistoryService.showAllMembers(TargetGroup);

                String res = "G#"+group_id+"#"+user.id+"#"+content+"#"+new Date();
                CenterServer.send2Group(userList,res);
            }
        }
    }

    /**
     * 提取初始化信息的核心方法
     * @param user 操作发起者 {@link entity.User}
     * @return 初始化信息
     * @throws MyException 无
     */
    public static String initialize(User user) throws MyException{
        int i;
        StringBuilder result= new StringBuilder("start#");
        //个人信息
        result.append(user.loginInfo()).append("#");

        StringBuilder group_item;
        synchronized (CenterServer.sqlSession){
            //好友列表
            List<User> friendList = HistoryService.getFriendList(user);
            for(i = 0;i<friendList.size();i++){
                result.append(friendList.get(i).friendInfo());
                if(i!=friendList.size()-1){
                    result.append("%%");
                }
            }
            result.append("#");

            List<Group> groupList = HistoryService.getGroupList(user);
            for(int u=0;u<groupList.size()-1;u++){System.out.println(groupList.get(u).getName());}

            //循环加入小组信息
            for(i = 0;i<groupList.size();i++){
                group_item = new StringBuilder();
                group_item.append(groupList.get(i));
                if(i!=groupList.size()-1){
                    group_item.append("@@");
                }
                result.append(group_item);
            }
            return result.toString();
        }
    }

    /**
     * 提取群聊成员表的核心方法
     * @param group 目标群聊 {@link entity.Group}
     * @return 携带有群聊成员表的答复信息
     * @throws MyException 无
     */
    public static String groupMembers(Group group) throws MyException{
        synchronized (CenterServer.sqlSession){
            List<User> members = HistoryService.showAllMembers(group);
            int j;
            //小组内循环加入成员信息
            StringBuilder memberListInfo = new StringBuilder("gm#");
            memberListInfo.append(group.id).append("#");
            for(j = 0;j<members.size();j++){
                memberListInfo.append(members.get(j).memberInfo());
                if(j!=members.size()-1){//尾巴不需要分节符
                    memberListInfo.append("#");
                }
            }
            return memberListInfo.toString();
        }
    }

    /**
     * 提取私聊历史的核心方法
     * @param self 操作发起者 {@link entity.User}
     * @param msg 客户端原信息
     * @return 答复信息
     * @throws MyException 二人没有好友关系
     */
    public static String privateHistory(User self,String msg)throws MyException{
        String[] attrs = msg.split("#");
        int target_user_id = Integer.parseInt(attrs[1]);

        User user_form=new User();
        user_form.setId(target_user_id);
        String target_user_name;
        List<PrivateMessage> primsg_list;
        StringBuilder result = new StringBuilder("ph#").append(target_user_id).append("#");
        int i;
        //获取记录数据
        synchronized (CenterServer.sqlSession){
            try{
                 target_user_name = UserService.searchUser(user_form).get(0).getUsername();
            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();
                throw new MyException(500);
            }
            primsg_list = HistoryService.getPrivateMessage(new Friends(target_user_id,self.getId()));
        }
        //根据记录填充响应句
        for(i=0;i<primsg_list.size();i++){
            if(primsg_list.get(i).getUser_from()==target_user_id){
                result.append(target_user_id).append("@@");
            }
            else{
                result.append(self.getId()).append("@@");
            }
            result.append(primsg_list.get(i).getPush_time()).append("@@").append(primsg_list.get(i).getContent());
            if(i!=primsg_list.size()-1){
                result.append("#");
            }
        }
        return result.toString();
    }

    /**
     * 提取群聊历史的核心方法
     * @param self 操作发起者 {@link entity.User}
     * @param msg 客户端原信息
     * @return 答复信息
     * @throws MyException 群聊不存在
     */
    public static String groupHistory(User self,String msg)throws MyException{
        String[] attrs = msg.split("#");
        int group_id = Integer.parseInt(attrs[1]);
        synchronized (CenterServer.sqlSession){
            Group group_form = new Group();
            Group group;
            group_form.setId(group_id);
            try{
                group = GroupService.findGroup(group_form).get(0);
            }catch(IndexOutOfBoundsException e){
                throw new MyException(500);
            }
            CenterServer.sendToSpecificUser(self.getId(),groupMembers(group));

            StringBuilder result = new StringBuilder("gh#").append(group_id).append("#");
            List<GroupMessage> groupMessagesList = HistoryService.getGroupMessage(group);
            int i;
            GroupMessage groupMessage;
            for(i=0;i<groupMessagesList.size();i++){
                groupMessage = groupMessagesList.get(i);
                result.append(groupMessage.getUser_id()).append("@@");
                result.append(groupMessage.getTime_stamp()).append("@@");
                result.append(groupMessage.getContent());
                if(i!=groupMessagesList.size()-1){
                    result.append("#");
                }
            }
            return result.toString();
        }
    }

    /**
     * 寻找用户的核心方法
     * @param msg 客户端原信息
     * @return 答复信息
     * @throws MyException 用户不存在
     */
    public static String searchUser(String msg) throws MyException{
        String[] attrs = msg.split("#");
        int user_id = Integer.parseInt(attrs[1]);
        synchronized (CenterServer.sqlSession){
            User user_form = new User();
            user_form.setId(user_id);
            List<User> userList= UserService.searchUser(user_form);
            if(userList.size()>0){
                User target_user = userList.get(0);
                String result = "sf#"+target_user.getId()+"#"+target_user.getUsername()+"#"+target_user.getAvatar();
                return result;
            }
            else{
                throw new MyException(408);
            }
        }
    }

    /**
     * 寻找群聊的核心方法
     * @param msg 客户端原信息
     * @return 答复信息
     * @throws MyException 群聊不存在
     */
    public static String searchGroup(String msg) throws MyException{
        String[] attrs = msg.split("#");
        int group_id = Integer.parseInt(attrs[1]);
        synchronized (CenterServer.sqlSession){
            Group group_form = new Group();
            group_form.setId(group_id);
            List<Group> groupList = GroupService.findGroup(group_form);
            if(groupList.size()>0){
                Group target_group = groupList.get(0);
                String result = "sg#"+target_group.getId()+"#"+target_group.getName()+"#"+target_group.getAvatar();
                return result;
            }
            else{
                throw new MyException(409);
            }
        }
    }
}
