package com.keunwon.algorithm.programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class Lesson17684 {
    public int[] solution(String msg) {
        var words = new HashMap<String, Integer>();
        var answer = new ArrayList<Integer>();

        for (char c = 'A'; c <= 'Z'; c++) {
            words.put(String.valueOf(c), words.size() + 1);
        }

        for (int i = 0; i < msg.length(); ) {
            var end = i + 1;
            while (end <= msg.length()) {
                if (!words.containsKey(msg.substring(i, end))) break;
                ++end;
            }

            var word = msg.substring(i, end - 1);
            answer.add(words.get(word));
            words.put(msg.substring(i, Math.min(end, msg.length())), words.size() + 1);
            i += word.length();
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
