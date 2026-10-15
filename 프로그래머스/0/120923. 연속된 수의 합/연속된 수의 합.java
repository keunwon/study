class Solution {
    public int[] solution(int num, int total) {
        var start = total / num - (num - 1) / 2;
        var res = new int[num];
        
        for (var i = 0; i < num; i++) {
            res[i] = start + i;
        }
        return res;
    }
}