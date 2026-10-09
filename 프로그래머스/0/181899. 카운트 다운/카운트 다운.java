class Solution {
    public int[] solution(int start_num, int end_num) {
        var n = start_num - end_num + 1;
        var res = new int[n];
        
        for (var i = 0; i < n; i++) {
            res[i] = start_num - i;
        }
        return res;
    }
}