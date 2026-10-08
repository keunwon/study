class Solution {
    public int[] solution(int[] num_list, int n) {
        var m = num_list.length;
        var res = new int[m];

        System.arraycopy(num_list, n, res, 0, m - n);
        System.arraycopy(num_list, 0, res, m - n, n);
        return res;
    }
}