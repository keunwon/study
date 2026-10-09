class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for (var q : queries) {
            var s = q[0];
            var e = q[1];
            var tmp = arr[s];
            arr[s] = arr[e];
            arr[e] = tmp;
        }
        return arr;
    }
}