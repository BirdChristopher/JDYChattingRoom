package com.jdy.Client.util;

import com.jdy.Client.ReceiveThread;
import com.jdy.Client.controller.ControllerFactory;
import com.jdy.Client.data.dataList.FriendList;
import com.jdy.Client.data.dataList.GroupList;
import com.jdy.Client.data.dataList.MemberList;
import com.jdy.Client.data.dataList.MessageList;
import com.jdy.Client.data.group.Group;
import com.jdy.Client.data.message.Message;
import com.jdy.Client.data.message.MessageType;
import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;
import javafx.scene.image.Image;
import sun.nio.cs.ext.GBK;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 数据处理类.
 *
 * 核心业务类，连接服务器，接收消息，处理消息，调用controller类更新视图.
 *
 * @author dh
 */
public class DataManager {
    private static DataManager instance;
    private DataManager() {}
    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * 连接服务器.
     * @throws IOException IO异常
     */
    public void connect() throws IOException {
        socket = new Socket("82.157.34.127", 8080);
        // socket = new Socket("10.136.112.180", 30000); // 测试代码
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        Thread receiveThread = new Thread(new ReceiveThread(in));
        receiveThread.start();
    }

    /**
     * 给服务器发送消息.
     * @param data 需要发送的消息
     */
    public void sent(String data) {
        out.println(data);
    }

    /**
     * 解析服务器发送来的消息，调用相应方法处理.
     * @param string 服务器发送来的消息
     */
    public void manage(String string) {
        String[] data = string.split("#");
        if (data.length != 0) {
            switch (data[0]) {
                case "login":
                    login(data);
                    break;
                case "register":
                    register(data);
                    break;
                case "add":
                    addFriend(data);
                    break;
                case "cgroup":
                    createGroup(data);
                    break;
                case "jgroup":
                    joinGroup(data);
                    break;
                case "sf":
                    searchFriend(data);
                    break;
                case "sg":
                    searchGroup(data);
                    break;
                case "G":
                    receiveGroupMessage(data);
                    break;
                case "P":
                    receivePrivateMessage(data);
                    break;
                case "start":
                    initInformation(data);
                    break;
                case "ph":
                    setPrivateHistory(data);
                    break;
                case "gm":
                    setGroupMember(data);
                    break;
                case "gh":
                    setGroupHistory(data);
                    break;
                case "upGroupM":
                    updateGroupMember(data);
                    break;
                case "upGroup":
                    updateGroup(data);
                    break;
                case "upFriend":
                    updateFriend(data);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 登录信息处理.
     * @param data 服务器发送来的消息
     */
    private void login(String[] data) {
        if (!"200".equals(data[1])) {
            ControllerFactory.getLoginController().loginFail(Integer.parseInt(data[1]));
        }
    }

    /**
     * 注册信息处理.
     * @param data 服务器发送来的消息
     */
    private void register(String[] data) {
        if ("200".equals(data[1])) {
            if (data.length == 3) {
                ControllerFactory.getRegisterController().success(data[2]);
            }
        } else {
            ControllerFactory.getRegisterController().fail();
        }
    }

    /**
     * 登录后，初始化CurrentUser, FriendList, GroupList信息.
     * @param data 服务器发送来的消息
     */
    private void initInformation(String[] data) {
        // 个人信息
        String[] myInfo = data[1].split("&");
        CurrentUser.getInstance().setUid(IdUtil.S2C(myInfo[1]));
        CurrentUser.getInstance().setName(myInfo[0]);
        CurrentUser.getInstance().setAvatar(new Image("/image/avatar/" + myInfo[2] + ".jpg"));
        CurrentUser.getInstance().setSex(myInfo[3]);
        CurrentUser.getInstance().setSignature(myInfo[4]);
        // 好友列表
        if (data.length > 2 && !data[2].equals("")) {
            String[] friends = data[2].split("%%");
            for (int i = 0; i < friends.length; ++i) {
                String[] str = friends[i].split("&");
                Image avatar = new Image("/image/avatar/" + str[2] + ".jpg");
                User user = new User(IdUtil.S2C(str[0]), str[1], avatar);
                FriendList.friends.add(user);
            }
        }
        // 群聊列表
        if (data.length >= 4) {
            String[] groups = data[3].split("@@");
            for (int i = 0; i < groups.length; ++i) {
                String[] str = groups[i].split("&");
                Image avatar = new Image("/image/avatar/" + str[2] + ".jpg");
                Group group = new Group("G" + IdUtil.S2C(str[0]), str[1], avatar);
                GroupList.groups.add(group);
            }
        }
        ControllerFactory.getLoginController().loginSuccess();
    }

    /**
     * 初始化私聊历史消息.
     * @param data 服务器发送来的消息
     */
    private void setPrivateHistory(String[] data) {
        String uid = IdUtil.S2C(data[1]);
        User friend = FriendList.getUserById(uid);
        User me = CurrentUser.getInstance();
        ArrayList<Message> list = MessageList.getList(uid);
        if (data.length > 2) {
            for (int i = 2; i < data.length; ++i) {
                String[] str = data[i].split("@@");
                String myid = IdUtil.S2C(str[0]);
                if (myid.equals(me.getUid()))
                    list.add(new Message(uid, me, str[2], MessageType.SENT));
                else
                    list.add(new Message(uid, friend, str[2], MessageType.RECEIVED));
            }
        }
        ArrayList<User>  list1 = MemberList.getList(uid);
        list1.add(me);
        list1.add(friend);
        ControllerFactory.getChatController(uid).init();
    }

    /**
     * 初始化群聊历史消息.
     * @param data 服务器发送来的消息
     */
    private void setGroupHistory(String[] data) {
        String id = "G" + IdUtil.S2C(data[1]);
        User me = CurrentUser.getInstance();
        ArrayList<Message> list = MessageList.getList(id);
        ArrayList<User> list1 = MemberList.getList(id);
        synchronized (list1) {
            if (data.length > 2) {
                for (int i = 2; i < data.length; ++i) {
                    String[] str = data[i].split("@@");
                    String myid = IdUtil.S2C(str[0]);
                    if (myid.equals(me.getUid()))
                        list.add(new Message(id, me, str[2], MessageType.SENT));
                    else
                        list.add(new Message(id, MemberList.getUserById(id, myid), str[2], MessageType.RECEIVED));
                }
            }
        }
        ControllerFactory.getChatController(id).init();
    }

    /**
     * 初始化群聊成员.
     * @param data 服务器发送来的消息
     */
    private void setGroupMember(String[] data) {
        String id = "G" + IdUtil.S2C(data[1]);
        User me = CurrentUser.getInstance();
        ArrayList<User> list = MemberList.getList(id);
        synchronized (list) {
            if (data.length > 2) {
                for (int i = 2; i < data.length; ++i) {
                    String[] str = data[i].split("@@");
                    User user = new User(IdUtil.S2C(str[0]), str[1], new Image("/image/avatar/" + str[2] + ".jpg"));
                    list.add(user);
                }
            }
        }
    }

    /**
     * 接收处理私聊历史消息.
     * @param data 服务器发送来的消息
     */
    private void receivePrivateMessage(String[] data) {
        String id = IdUtil.S2C(data[1]);
        String content = data[2];
        User user = FriendList.getUserById(id);
        ControllerFactory.getChatController(id).updateMessage(id, user, content);
    }

    /**
     * 接收处理群聊历史消息.
     * @param data 服务器发送来的消息
     */
    private void receiveGroupMessage(String[] data) {
        String gid = "G" + IdUtil.S2C(data[1]);
        String uid = IdUtil.S2C(data[2]);
        if (!uid.equals(CurrentUser.getInstance().getUid())) {
            String content = data[3];
            User user = MemberList.getUserById(gid, uid);
            System.out.println(user);
            ControllerFactory.getChatController(gid).updateMessage(gid, user, content);
        }
    }

    /**
     * 更新好友信息和列表.
     * @param data 服务器发送来的消息
     */
    private void updateFriend(String[] data) {
        String uid = IdUtil.S2C(data[1]);
        User user = new User(uid, data[2], new Image("/image/avatar/" + data[3] + ".jpg"));
        ControllerFactory.getHomeController().addFriend(user);
        FriendList.friends.add(user);
    }

    /**
     * 更新群聊成员信息和列表.
     * @param data 服务器发送来的消息
     */
    private void updateGroupMember(String[] data) {
        String gid = "G" + IdUtil.S2C(data[1]);
        String uid = IdUtil.S2C(data[2]);
        User user = new User(uid, data[3], new Image("/image/avatar/" + data[4] + ".jpg"));
        ControllerFactory.getChatController(gid).addMember(user);
    }

    /**
     * 更新群聊信息和列表.
     * @param data 服务器发送来的消息
     */
    private void updateGroup(String[] data) {
        String gid = "G" + IdUtil.S2C(data[1]);
        Group group = new Group(gid, data[2], new Image("/image/avatar/" + data[3] + ".jpg"));
        ControllerFactory.getHomeController().addGroup(group);
        GroupList.groups.add(group);
    }

    /**
     * 处理查找群聊的消息.
     * @param data 服务器发送来的消息
     */
    private void searchGroup(String[] data) {
        Group group = null;
        if (data.length > 2) {
            String gid = "G" + IdUtil.S2C(data[1]);
            String name = data[2];
            Image avatar = new Image("/image/avatar/" + data[3] + ".jpg");
            group = new Group(gid, name, avatar);
        }
        ControllerFactory.getLookUpGroupController().setResult(group);
    }

    /**
     * 处理查找好友的消息.
     * @param data 服务器发送来的消息
     */
    private void searchFriend(String[] data) {
        User user = null;
        if (data.length > 2) {
            String uid = IdUtil.S2C(data[1]);
            String name = data[2];
            Image avatar = new Image("/image/avatar/" + data[3] + ".jpg");
            user = new User(uid, name, avatar);
        }
        ControllerFactory.getLookUpFriendController().setResult(user);
    }

    /**
     * 处理加入群聊的消息(默认成功不做处理).
     * @param data 服务器发送来的消息
     */
    private void joinGroup(String[] data) {
        /*if ("200".equals(data[2]))
            ControllerFactory.getHomeController().addGroup();*/
    }

    /**
     * 处理创建群聊的消息.
     * @param data 服务器发送来的消息
     */
    private void createGroup(String[] data) {
        if ("200".equals(data[1])) {
            String gid = "G" + IdUtil.S2C(data[2]);
            String name = data[3];
            Image avatar = new Image("/image/avatar/" + data[4] + ".jpg");
            Group group = new Group(gid, name, avatar);
            GroupList.groups.add(group);
            ControllerFactory.getHomeController().addGroup(group);
        }
    }

    /**
     * 处理添加好友的消息（默认成功不做处理）.
     * @param data 服务器发送来的消息
     */
    private void addFriend(String[] data) {

    }
}
