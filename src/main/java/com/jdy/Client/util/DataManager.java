package com.jdy.Client.util;

import com.jdy.Client.ReceiveThread;
import com.jdy.Client.controller.ControllerFactory;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class DataManager {
    private static DataManager instance;
    private DataManager() {
        loginStatus = LoginStatus.WAITING;
    }
    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    private Socket socket;
    private BufferedReader in;
    private PrintStream out;

    private LoginStatus loginStatus;
    private String uid;
    private RegisterStatus registerStatus;
    private boolean imageConfirmed;

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
                    login(data[1]);
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
                    //receiveMessage(data);
                    break;
                case "start":
                    break;
                default:
                    break;
            }
        }
    }
    private void login(String value) {
        switch (value) {
            case "1":
                loginStatus = LoginStatus.SUCCESS;
                break;
            case "0":
                loginStatus = LoginStatus.PASSWORD_ERROR;
                break;
            case "-1":
                loginStatus = LoginStatus.NONEXISTENT;
                break;
            case "-2":
                loginStatus = LoginStatus.LOGGED_IN;
                break;
            default:
                break;
        }
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
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

    public String getUid() {
        return uid;
    }

    public RegisterStatus getRegisterStatus() {
        return registerStatus;
    }

    public void setImageConfirmed(boolean imageConfirmed) {
        this.imageConfirmed = imageConfirmed;
    }

    public boolean isImageConfirmed() {
        return imageConfirmed;
    }
}
