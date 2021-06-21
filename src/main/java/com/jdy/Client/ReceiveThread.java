package com.jdy.Client;

import com.jdy.Client.util.DataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

/**
 * 接收消息的线程类.
 *
 * 一直监听服务器消息的线程.
 *
 * @author dh
 */
public class ReceiveThread implements Runnable{
    BufferedReader bufferedReader;

    public ReceiveThread(BufferedReader bufferedReader) throws IOException {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
            String receive = null;
            try {
                while ((receive = bufferedReader.readLine()) != null){
                    System.out.println("[Receive] " + new Date() + ": " + receive);
                    DataManager.getInstance().manage(receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
