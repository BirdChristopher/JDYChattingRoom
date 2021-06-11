package com.jdy.Client.util;

/**
 * id处理类.
 *
 * 因为数据库采用的自增获取id，这个单纯为了让id变为6位数好看.
 *
 * @author dh
 */
public class IdUtil {
    /**
     * 数据库id转为客户端id.
     * @param sid id
     * @return id
     */
    public static String S2C(String sid) {
        if (sid.matches("\\d+")) {
            int temp = Integer.parseInt(sid) + 100000;
            return String.valueOf(temp);
        }
        return null;
    }

    /**
     * 客户端id转为数据库id.
     * @param cid id
     * @return id
     */
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
