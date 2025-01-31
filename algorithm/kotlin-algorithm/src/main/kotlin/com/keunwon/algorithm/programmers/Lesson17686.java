package com.keunwon.algorithm.programmers;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Lesson17686 {
    public String[] solution(String[] files) {
        return Arrays.stream(files)
                .map(Filename::new)
                .sorted()
                .map(Filename::getOriginal)
                .toArray(String[]::new);
    }

    private static class Filename implements Comparable<Filename> {
        private static final Pattern NAME_PATTERN = Pattern.compile("(\\D+)(\\d+)([\\S\\s]*)");

        private final String original;
        private final String head;
        private final Integer number;
        private final String tail;

        Filename(String text) {
            var matcher = NAME_PATTERN.matcher(text);
            if (!matcher.matches()) throw new IllegalArgumentException("text not support: " + text);

            this.original = text;
            this.head = matcher.group(1);
            this.number = Integer.parseInt(matcher.group(2));
            this.tail = matcher.group(3);
        }

        @Override
        public int compareTo(@NotNull Filename o) {
            if (!head.equalsIgnoreCase(o.head)) return head.compareToIgnoreCase(o.head);
            else if (!number.equals(o.number)) return number.compareTo(o.number);
            else return 0;
        }

        public String getOriginal() {
            return original;
        }
    }
}
