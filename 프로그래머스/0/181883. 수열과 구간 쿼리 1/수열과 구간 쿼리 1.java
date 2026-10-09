class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for (var q : queries) {
            var s = q[0];
            var e = q[1];
            for (var i = s; i <= e; i++) {
                ++arr[i];
            }
        }
        return arr;
    }
}