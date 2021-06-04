package com.jdy.Client;

import com.jdy.Client.util.DataManager;

import java.io.BufferedReader;
import java.io.IOException;

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
                    System.out.println(getClass().getSimpleName() + ": " + receive);
                    DataManager.getInstance().manage(receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
