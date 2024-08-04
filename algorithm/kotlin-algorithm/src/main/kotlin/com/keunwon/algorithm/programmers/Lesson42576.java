package com.keunwon.algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

public class Lesson42576 {
    public String solution(String[] participant, String[] completion) {
        var pMap = countMap(participant);
        var cMap = countMap(completion);
        var result = "";

        for (var key : pMap.keySet()) {
            if (!pMap.get(key).equals(cMap.getOrDefault(key, 0))) {
                result = key;
            }
        }
        return result;
    }

    private Map<String, Integer> countMap(String[] arr) {
        var map = new HashMap<String, Integer>();
        for (var str : arr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        return map;
    }
}
