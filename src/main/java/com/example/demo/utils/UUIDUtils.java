package com.example.demo.utils;

import java.util.UUID;

/**
 * @author Smile(wangyajun)
 * @create 2018-09-14 9:29
 **/
public class UUIDUtils {

    public static String generateUUIDjdk(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
