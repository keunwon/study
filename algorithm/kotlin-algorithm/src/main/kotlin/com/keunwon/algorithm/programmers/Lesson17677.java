package com.keunwon.algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

public class Lesson17677 {
    public int solution(String str1, String str2) {
        var map1 = countMap(str1);
        var map2 = countMap(str2);
        var iter = 0.0;
        var union = 0.0;

        if (map1.isEmpty() && map2.isEmpty()) return 65536;

        for (var key : map1.keySet()) {
            if (map2.containsKey(key)) {
                iter += Math.min(map1.get(key), map2.get(key));
                union += Math.max(map1.get(key), map2.get(key));
                map2.remove(key);
            } else {
                union += map1.get(key);
            }
        }

        for (var key : map2.keySet()) {
            union += map2.get(key);
        }
        return (int) Math.floor(iter / union * 65536);
    }

    private Map<String, Integer> countMap(String text) {
        var arr = text.toLowerCase().toCharArray();
        var countMap = new HashMap<String, Integer>();

        for (int i = 0; i < text.length() - 1; i++) {
            var c1 = arr[i];
            var c2 = arr[i + 1];

            if (isAlphabet(c1) && isAlphabet(c2)) {
                var key = String.valueOf(c1).concat(String.valueOf(c2));
                countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            }
        }
        return countMap;
    }

    private boolean isAlphabet(char c) {
        return c >= 'a' && c <= 'z';
    }
}
