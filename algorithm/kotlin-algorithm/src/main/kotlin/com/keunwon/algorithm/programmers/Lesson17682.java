package com.keunwon.algorithm.programmers;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Lesson17682 {
    public int solution(String dartResult) {
        var pattern = Pattern.compile("([0-9]+)([SDT])([*#]?)");
        var matcher = pattern.matcher(dartResult);
        var numbers = new ArrayList<Integer>();
        var index = 0;

        while (matcher.find()) {
            var n = switch (matcher.group(2)) {
                case "T":
                    yield 3;
                case "D":
                    yield 2;
                default:
                    yield 1;
            };
            var option = switch (matcher.group(3)) {
                case "*":
                    yield 2;
                case "#":
                    yield -1;
                default:
                    yield 1;
            };
            var score = (int) Math.pow(Integer.parseInt(matcher.group(1)), n) * option;

            numbers.add(score);

            if (index > 0 && option == 2) {
                numbers.set(index - 1, numbers.get(index - 1) * 2);
            }
            ++index;
        }
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
}
