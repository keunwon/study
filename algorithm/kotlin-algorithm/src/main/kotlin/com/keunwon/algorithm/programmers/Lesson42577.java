package com.keunwon.algorithm.programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Lesson42577 {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        var set = Arrays.stream(phone_book).collect(Collectors.toSet());

        for (String phone : phone_book) {
            for (int i = 1; i < phone.length(); i++) {
                var tmp = phone.substring(0, i);
                if (set.contains(tmp)) return false;
            }
        }
        return true;
    }
}
