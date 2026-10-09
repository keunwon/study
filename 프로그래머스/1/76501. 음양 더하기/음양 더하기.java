class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        var n = absolutes.length;
        var res = 0;
        
        for (var i = 0; i < n; i++) {
            res += signs[i] ? absolutes[i] : -absolutes[i];
        }
        return res;
    }
}