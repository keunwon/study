import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public long solution(int[] weights) {
        Arrays.sort(weights);

        var count = new HashMap<Double, Integer>();
        var res = 0L;

        for (var w : weights) {
            var a = w * 1.0;
            var b = w * 2.0 / 3;
            var c = w * 2.0 / 4;
            var d = w * 3.0 / 4;

            res += count.getOrDefault(a, 0);
            res += count.getOrDefault(b, 0);
            res += count.getOrDefault(c, 0);
            res += count.getOrDefault(d, 0);
            
            count.put(a, count.getOrDefault(a, 0) + 1);
        }
        return res;
    }
}