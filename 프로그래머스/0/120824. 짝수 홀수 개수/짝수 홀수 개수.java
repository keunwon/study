class Solution {
    public int[] solution(int[] num_list) {
        var res = new int[2];
        for (var num : num_list) {
            if (num % 2 == 0) ++res[0];
            else ++res[1];
        }
        return res;
    }
}