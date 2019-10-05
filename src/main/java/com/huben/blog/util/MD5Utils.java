package com.huben.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author koishi
 */
public class MD5Utils {
    public static void main(String[] args) {
        System.out.println(MD5Utils.MD5("admin"));
    }
    public static String MD5(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] byteDigest = md5.digest();
            int i;
            StringBuilder sb = new StringBuilder("");
            for (byte b : byteDigest) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
