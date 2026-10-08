class Solution {
    public int solution(int[] num_list, int n) {
        for (var num : num_list) {
            if (num == n) return 1;
        }
        return 0;
    }
}