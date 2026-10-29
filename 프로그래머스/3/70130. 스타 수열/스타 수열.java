import java.util.Arrays;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Solution {
    public int solution(int[] a) {
        var countMap = Arrays.stream(a).boxed().collect(groupingBy(Function.identity(), counting()));
        var maxCount = 0;

        for (var e : countMap.entrySet()) {
            if (maxCount > e.getValue()) continue;

            var target = (int) e.getKey();
            var idx = 0;
            var tmpCount = 0;

            while (idx < a.length - 1) {
                if (a[idx] != a[idx + 1] && (a[idx] == target || a[idx + 1] == target)) {
                    ++idx;
                    ++tmpCount;
                }
                ++idx;
            }
            maxCount = Math.max(maxCount, tmpCount);
        }
        return maxCount * 2;
    }
}
