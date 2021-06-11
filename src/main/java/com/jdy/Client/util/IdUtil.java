package com.jdy.Client.util;

public class IdUtil {
    public static String S2C(String sid) {
        if (sid.matches("\\d+")) {
            int temp = Integer.parseInt(sid) + 100000;
            return String.valueOf(temp);
        }
        return null;
    }

    public static String C2S(String cid) {
        String res = null;
        if (cid.matches("\\d{6}")) {
            int temp = Integer.parseInt(cid) - 100000;
            res = String.valueOf(temp);
        } else if(cid.matches("G\\d{6}")) {
            int temp = Integer.parseInt(cid.substring(1)) - 100000;
            res = String.valueOf(temp);
        }
        return res;
    }
}
