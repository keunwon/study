class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        var n = queries.length;
        var res = new int[n];

        for (var i = 0; i < queries.length; i++) {
            var q = queries[i];
            var x = -1;

            for (var j = q[0]; j <= q[1]; j++) {
                if (arr[j] > q[2]) {
                    if (x == -1) x = arr[j];
                    else if (x > arr[j]) x = arr[j];
                }
            }
            res[i] = x;
        }
        return res;
    }
}