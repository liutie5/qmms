package com.qmms;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

public class TestRun {
    public static void main(String[] args) {
//        System.out.println(DigestUtils.md5Hex("e10adc3949ba59abbe56e057f20f883e"+"liutie099BMBUsSWfJ2BNP"));

         SimpleHash hash = new SimpleHash("md5", "e10adc3949ba59abbe56e057f20f883e","liutie099BMBUsSWfJ2BNP" , 1);
         System.out.println(hash);
         //826c01189d85b77a3cbc7485c496fee1
         hash = new SimpleHash("md5", "123456","liutie099BMBUsSWfJ2BNP" , 1);

        System.out.println(hash.toString());

    }
}
