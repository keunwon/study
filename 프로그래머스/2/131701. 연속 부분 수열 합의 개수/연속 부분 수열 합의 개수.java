import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        var n = elements.length;
        var prefixSum = new int[n * 2 + 1];

        for (var i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + elements[(i - 1) % n];
        }

        var set = new HashSet<Integer>();
        for (var s = 0; s < n; s++) {
            for (var e = s; e < s + n; e++) {
                var sum = prefixSum[e] - prefixSum[s];
                set.add(sum);
            }
        }
        return set.size();
    }
}