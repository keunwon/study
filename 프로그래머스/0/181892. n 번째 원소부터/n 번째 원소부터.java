class Solution {
    public int[] solution(int[] num_list, int n) {
        var m = num_list.length - n + 1;
        var res = new int[m];
        var nIdx = n - 1;
        
        for (var i = 0; i < m; i++) {
            res[i] = num_list[nIdx++];
        }
        return res;
    }
}