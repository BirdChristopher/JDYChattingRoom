package com.jdy.Client.util;

import com.jdy.Client.ReceiveThread;
import com.jdy.Client.controller.LoginController;
import com.jdy.Client.data.ControllerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class DataManager {
    private static DataManager instance;
    private DataManager() {
        loginStatus = -3;
    }
    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private int loginStatus;

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
                    setLoginStatus(Integer.parseInt(data[1]));
                    break;
                case "register":
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
                    break;
                default:
                    break;
            }
        }
    }

    public void setLoginStatus(int value) {
        loginStatus = value;
    }

    public int getLoginStatus() {
        return loginStatus;
    }
}
