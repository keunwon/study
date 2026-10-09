class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for (var q : queries) {
            for (var i = q[0]; i <= q[1]; i++) {
                if (i % q[2] == 0) {
                    ++arr[i];
                }
            }
        }
        return arr;
    }
}