import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int[] query) {
        var l = 0;
        var r = arr.length - 1;

        for (var i = 0; i < query.length; i++) {
            var q = query[i];
            if (i % 2 == 0) {
                r = l + q;
            } else {
                l = l + q;
            }
        }
        return Arrays.copyOfRange(arr, l, r + 1);
    }
}