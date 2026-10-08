class Solution {
    public int[] solution(int[] num_list, int n) {
        var m = num_list.length;
        var res = new int[m];

        for (var i = 0; i < m; i++) {
            res[i] = num_list[(i + n) % m];
        }
        return res;
    }
}