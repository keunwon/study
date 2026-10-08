class Solution {
    public int solution(int[] array, int height) {
        var count = 0;
        for (var a : array) {
            if (a > height) ++count;
        }
        return count;
    }
}