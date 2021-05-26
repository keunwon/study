package com.keunwon.chapter02;

import java.util.ArrayList;
import java.util.HashMap;

public class DummyData {
    private HashMap<String, String> map;
    private ArrayList<String> list;

    public DummyData(HashMap<String, String> map, ArrayList<String> list) {
        this.map = map;
        this.list = list;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public ArrayList<String> getList() {
        return list;
    }
}
