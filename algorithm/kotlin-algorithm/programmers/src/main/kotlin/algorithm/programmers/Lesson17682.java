package algorithm.programmers;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Lesson17682 {
    public int solution(String dartResult) {
        final var pattern = Pattern.compile("([0-9]+)([SDT])([*#]?)");
        final var matcher = pattern.matcher(dartResult);
        final var numbers = new ArrayList<Integer>();

        while (matcher.find()) {
            final var n = switch (matcher.group(2)) {
                case "T" -> 3;
                case "D" -> 2;
                default -> 1;
            };

            final var option = switch (matcher.group(3)) {
                case "*" -> 2;
                case "#" -> -1;
                default -> 1;
            };

            final var point = (int) Math.pow(Integer.parseInt(matcher.group(1)), n) * option;

            if (option == 2 && !numbers.isEmpty()) {
                numbers.set(numbers.size() - 1, numbers.get(numbers.size() - 1) * 2);
            }
            numbers.add(point);
        }

        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

}
