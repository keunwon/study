package com.keunwon.chapter02;

import java.util.Properties;
import java.util.Set;

public class GetProperties {

    public static void main(String[] args) {
        System.setProperty("JavaTuning", "Tune Lee");
        Properties prop = System.getProperties();
        Set<Object> key = prop.keySet();

        for (Object o : key) {
            String curKey = o.toString();
            System.out.format("%s=%s \n", curKey, prop.getProperty(curKey));
        }
    }
}
