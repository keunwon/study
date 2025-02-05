package com.keunwon.algorithm.programmers;

import java.util.regex.Pattern;

public class Lesson1835_2 {
    private final Pattern DATA_FORMAT = Pattern.compile("([A-Z])~([A-Z])([>=<])(\\d)");
    private final char[] members = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private final boolean[] visited = new boolean[members.length];

    private int result = 0;

    public int solution(int n, String[] data) {
        combination(0, new char[members.length], data);
        return result;
    }

    private void combination(int depth, char[] picks, String[] data) {
        if (depth == picks.length) {
            for (var str : data) {
                var matcher = DATA_FORMAT.matcher(str);
                if (!matcher.matches()) return;

                var m1 = matcher.group(1);
                var m2 = matcher.group(2);
                var op = matcher.group(3);
                var between = Integer.parseInt(matcher.group(4));

                var mIndex1 = -1;
                var mIndex2 = -1;

                for (var i = 0; i < picks.length; i++) {
                    var p = String.valueOf(picks[i]);

                    if (p.equals(m1)) mIndex1 = i;
                    else if (p.equals(m2)) mIndex2 = i;
                }

                if (mIndex1 < 0 || mIndex2 < 0) return;

                var diff = Math.abs(mIndex1 - mIndex2) - 1;

                if (op.equals("=") && diff != between) return;
                else if (op.equals("<") && diff >= between) return;
                else if (op.equals(">") && diff <= between) return;
            }

            ++result;
            return;
        }

        for (var i = 0; i < members.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                picks[depth] = members[i];
                combination(depth + 1, picks, data);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        var n = 2;
        var data = new String[]{"N~F=0", "R~T>2"};

        var result = new Lesson1835_2().solution(n, data);
        System.out.println(result);
    }
}
