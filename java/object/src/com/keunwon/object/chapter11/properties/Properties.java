package com.keunwon.object.chapter11.properties;

import java.util.Hashtable;

public class Properties {
    private Hashtable<String, String> properteis = new Hashtable<>();

    public String setProperties(String key, String value) {
        return properteis.put(key, value);
    }

    public String getProperties(String key) {
        return properteis.get(key);
    }
}
