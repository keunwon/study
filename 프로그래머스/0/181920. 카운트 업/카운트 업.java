class Solution {
    public int[] solution(int start_num, int end_num) {
        var n = end_num - start_num + 1;
        var res = new int[n];
        
        for (var i = 0; i < n; i++) {
            res[i] = start_num + i;
        }
        return res;
    }
}