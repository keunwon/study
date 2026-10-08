class Solution {
    public int[] solution(int[] num_list) {
        var n = num_list.length;
        var res = new int[n + 1];

        System.arraycopy(num_list, 0, res, 0, n);
        res[n] = num_list[n - 2] >= num_list[n - 1] 
            ? num_list[n - 1] * 2 
            : num_list[n - 1] - num_list[n - 2];
        return res;
    }
}