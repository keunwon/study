class Solution {
    public int solution(int[] a, int[] b) {
        var res = 0;
        for (var i = 0; i < a.length; i++) {
            res += (a[i] * b[i]);
        }
        return res;
    }
}