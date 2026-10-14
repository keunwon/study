import java.util.regex.Pattern;

class Solution {
    public int solution(String dartResult) {
        var pattern = Pattern.compile("(\\d+)([STD])+([*#]?)");
        var matcher = pattern.matcher(dartResult);
        var numbers = new int[3];
        var nIdx = 0;

        while (matcher.find()) {
            var point = Integer.parseInt(matcher.group(1));
            var bonus = matcher.group(2);
            var option = matcher.group(3);

            switch (bonus) {
                case "D" -> point = point * point;
                case "T" -> point = point * point * point;
            }

            if (option.equals("*")) {
                point *= 2;
                if (nIdx > 0) numbers[nIdx - 1] *= 2;
            } else if (option.equals("#")) {
                point = -point;
            }
            numbers[nIdx++] = point;
        }

        var res = 0;
        for (var num : numbers) res += num;
        return res;
    }
}