package com.keunwon.chapter02;

import java.util.Map;
import java.util.Set;

public class GetEnv {

    public static void main(String[] args) {
        Map<String, String> envMap = System.getenv();
        Set<String> key = envMap.keySet();

        for (String s : key) {
            System.out.format("%s = %s \n", s, envMap.get(s));
        }
    }
}
