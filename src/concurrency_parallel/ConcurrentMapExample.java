package com.globalsoftwaresupport;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapExample {

    public static void main(String[] args) {

        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();


        // slipping condition
        if(!map.containsKey("key"))
            map.put("key", "value");

        // solution
        map.putIfAbsent("key", "value");
    }
}
