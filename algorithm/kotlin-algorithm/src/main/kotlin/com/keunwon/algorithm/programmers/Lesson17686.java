package com.keunwon.algorithm.programmers;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lesson17686 {
    public String[] solution(String[] files) {
        var fileNames = Arrays.stream(files).map(FileName::new).sorted().collect(Collectors.toList());
        var answer = new String[fileNames.size()];

        for (int i = 0; i < fileNames.size(); i++) {
            answer[i] = fileNames.get(i).original;
        }
        return answer;
    }

    private static class FileName implements Comparable<FileName> {
        private static final Pattern NAME_PATTERN = Pattern.compile("(\\D+)(\\d+)([\\S\\s]*)");

        String original;
        String head;
        Integer number;
        String tail;

        FileName(String text) {
            var matcher = NAME_PATTERN.matcher(text);
            if (!matcher.matches()) throw new IllegalArgumentException("text not support: " + text);

            this.original = text;
            this.head = matcher.group(1);
            this.number = Integer.parseInt(matcher.group(2));
            this.tail = matcher.group(3);
        }

        @Override
        public int compareTo(@NotNull FileName o) {
            if (!head.equalsIgnoreCase(o.head)) return head.compareToIgnoreCase(o.head);
            else if (!number.equals(o.number)) return number.compareTo(o.number);
            else return 0;
        }
    }
}
