import java.util.Arrays;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class Solution {
    public String solution(String[] participant, String[] completion) {
        var map = Arrays.stream(completion).collect(groupingBy(identity(), counting()));

        for (var p : participant) {
            var n = map.getOrDefault(p, 0L);
            if (n == 0L) {
                return p;
            } else if (n == 1L) {
                map.remove(p);
            } else {
                map.put(p, n - 1L);
            }
        }
        return "";
    }
}