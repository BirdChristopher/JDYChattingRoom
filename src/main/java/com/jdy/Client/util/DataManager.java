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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

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
    private PrintStream out;

    public void connect() throws IOException {
        socket = new Socket("10.135.224.45", 8080);
        // socket = new Socket("10.136.112.180", 30000); // 测试代码
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
        Thread receiveThread = new Thread(new ReceiveThread(in));
        receiveThread.start();
    }

    public void sent(String data) {
        out.println(data);
    }

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

    private void login(String[] data) {
        if (!"200".equals(data[1])) {
            ControllerFactory.getLoginController().loginFail(Integer.parseInt(data[1]));
        }
    }

    private void register(String[] data) {
        if ("200".equals(data[1])) {
            if (data.length == 3) {
                ControllerFactory.getRegisterController().success(data[2]);
            }
        } else {
            ControllerFactory.getRegisterController().fail();
        }
    }

    private void initInformation(String[] data) {
        // 个人信息
        String[] myInfo = data[1].split("&");
        CurrentUser.getInstance().setUid(IdUtil.S2C(myInfo[1]));
        CurrentUser.getInstance().setName(myInfo[0]);
        CurrentUser.getInstance().setAvatar(new Image("/image/avatar/" + myInfo[2] + ".jpg"));
        CurrentUser.getInstance().setSex(myInfo[3]);
        CurrentUser.getInstance().setSignature(myInfo[4]);
        // 好友列表
        if (!data[2].equals("")) {
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

    private void receivePrivateMessage(String[] data) {
        String id = IdUtil.S2C(data[1]);
        String content = data[2];
        User user = FriendList.getUserById(id);
        ControllerFactory.getChatController(id).updateMessage(id, user, content);
    }

    private void receiveGroupMessage(String[] data) {
        String gid = "G" + IdUtil.S2C(data[1]);
        String uid = IdUtil.S2C(data[2]);
        String content = data[3];
        User user = MemberList.getUserById(gid, uid);
        ControllerFactory.getChatController(gid).updateMessage(gid, user, content);
    }

    private void updateFriend(String[] data) {
        String uid = IdUtil.S2C(data[1]);
        User user = new User(uid, data[2], new Image("/image/avatar/" + data[3] + "./jpg"));
        ControllerFactory.getHomeController().addFriend(user);
        FriendList.friends.add(user);
    }

    private void updateGroupMember(String[] data) {
        String gid = "G" + IdUtil.S2C(data[1]);
        String uid = IdUtil.S2C(data[2]);
        User user = new User(uid, data[3], new Image("/image/avatar/" + data[4] + "./jpg"));
        ControllerFactory.getChatController(gid).addMember(user);
    }

    private void updateGroup(String[] data) {
        String gid = "G" + IdUtil.S2C(data[1]);
        Group group = new Group(gid, data[2], new Image("/image/avatar/" + data[3] + "./jpg"));
        ControllerFactory.getHomeController().addGroup(group);
        GroupList.groups.add(group);
    }

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

    private void joinGroup(String[] data) {
        /*if ("200".equals(data[2]))
            ControllerFactory.getHomeController().addGroup();*/
    }

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

    private void addFriend(String[] data) {

    }
}
