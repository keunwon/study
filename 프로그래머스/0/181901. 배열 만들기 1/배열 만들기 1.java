class Solution {
    public int[] solution(int n, int k) {
        var m = n / k;
        var res = new int[m];
        
        for (var i = 0; i < m; i++) {
            res[i] = (i + 1) * k;
        }
        return res;
    }
}