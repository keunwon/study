import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        var strs = new String[numbers.length];

        for (var i = 0; i < numbers.length; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strs, (o1, o2) -> {
            var s1 = o1 + o2;
            var s2 = o2 + o1;
            return s2.compareTo(s1);
        });

        var sb = new StringBuilder();
        for (var s : strs) sb.append(s);
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }
}