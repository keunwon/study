import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int[] solution(int[] arr, int k) {
        var res = new int[k];
        var rIdx = 0;
        var set = new HashSet<Integer>(k);

        Arrays.fill(res, -1);

        for (var a : arr) {
            if (!set.contains(a)) {
                set.add(a);
                res[rIdx++] = a;
                if (rIdx == k) break;
            }
        }
        return res;
    }
}