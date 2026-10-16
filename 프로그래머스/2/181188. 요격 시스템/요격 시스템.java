import java.util.Arrays;

import static java.util.Comparator.comparingInt;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, comparingInt((int[] arr) -> arr[1]).thenComparingInt(o -> o[0]));

        var end = targets[0][1];
        var res = 1;
        
        for (var i = 1; i < targets.length; i++) {
            var t = targets[i];
            if (end <= t[0]) {
                end = t[1];
                ++res;
            }
        }
        return res;
    }
}