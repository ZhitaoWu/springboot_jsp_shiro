package com.wzt.springboot_jsp_shiro.utils;

import java.util.Random;

/**
 * @User:Tao
 * @date:2021/1/7
 * Salt
 */
public class SaltUtil {
    /**
     * 生成salt的静态方法
     * @param num
     * @return
     */
    public static String getSalt(int num) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        // 将该字符串转为字符数组
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            // 从chars数组中随机取出一个字符
            char aChar = chars[new Random().nextInt(chars.length)];
            // 将取到的随机字符存入StringBuilder数组中
            sb.append(aChar);

        }
        // StringBuilder转String 返回
        return sb.toString();
    }

    // 测试
    public static void main(String[] args) {
        // 8位的随机盐
        String salt = getSalt(8);
        System.out.println(salt);
    }
}
