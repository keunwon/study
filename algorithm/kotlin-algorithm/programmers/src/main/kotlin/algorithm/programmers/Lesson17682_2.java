package algorithm.programmers;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Lesson17682_2 {
    public int solution(String dartResult) {
        var pattern = Pattern.compile("([0-9]0?)([SDT])([*#]?)");
        var matcher = pattern.matcher(dartResult);
        var numbers = new int[3];
        var nIndex = 0;

        while (matcher.find()) {
            var point = Integer.parseInt(matcher.group(1));
            var n = switch (matcher.group(2)) {
                case "D" -> point * point;
                case "T" -> point * point * point;
                default -> point;
            };
            var option = switch (matcher.group(3)) {
                case "*" -> 2;
                case "#" -> -1;
                default -> 1;
            };

            numbers[nIndex] = n * option;

            if (nIndex > 0 && option == 2) {
                numbers[nIndex - 1] = numbers[nIndex - 1] * 2;
            }
            ++nIndex;
        }
        return Arrays.stream(numbers).sum();
    }
}
