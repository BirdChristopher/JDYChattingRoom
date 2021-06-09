package com.jdy.Client.util;

import com.jdy.Client.App;
import com.jdy.Client.ReceiveThread;
import com.jdy.Client.controller.ControllerFactory;
import com.jdy.Client.data.addressList.FriendList;
import com.jdy.Client.data.addressList.GroupList;
import com.jdy.Client.data.group.Group;
import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;
import com.sun.org.apache.bcel.internal.generic.FCMPG;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

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
        socket = new Socket("10.136.112.180", 30000);
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
                    break;
                case "cgroup":
                    break;
                case "jgroup":
                    break;
                case "G":
                    break;
                case "P":
                    break;
                case "start":
                    initInformation(data);
                    break;
                default:
                    break;
            }
        }
    }
    private void login(String[] data) {
        if ("200".equals(data[1])) {
            DataManager.getInstance().sent("success"); // 测试代码
        } else {
            ControllerFactory.getLoginController().loginFail(Integer.parseInt(data[1]));
        }
    }

    private void register(String[] data) {
        if ("1".equals(data[1])) {
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
        CurrentUser.getInstance().setUid(myInfo[0]);
        CurrentUser.getInstance().setName(myInfo[1]);
        CurrentUser.getInstance().setAvatar(new Image("/image/avatar/" + myInfo[2] + ".jpg"));
        CurrentUser.getInstance().setSex(myInfo[3]);
        CurrentUser.getInstance().setSignature(myInfo[4]);
        // 好友列表
        if (data.length >= 3) {
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
}
