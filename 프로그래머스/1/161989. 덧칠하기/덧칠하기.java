class Solution {
    public int solution(int n, int m, int[] section) {
        var top = -1;
        var res = 0;

        for (var s : section) {
            if (top < s) {
                top = s + m - 1;
                ++res;
            }
        }
        return res;
    }
}