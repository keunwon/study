class Solution {
    public int solution(int[] array, int n) {
        var count = 0;
        for (var a : array) {
            if (a == n) ++count;
        }
        return count;
    }
}