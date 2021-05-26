package com.jdy.Client.data;

public class User {
    private final String uid;
    private String name;
    private String password;

    User(String name, String password) {
        this.uid = generateId();
        this.name = name;
        this.password = password;
    }

    private String generateId() {
        // TODO: 自动生成ID
        return "id";
    }
}
