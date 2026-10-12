class Solution {
    public int[] solution(int n, long left, long right) {
        var size = (int) (right - left + 1);
        var res = new int[size];

        for (var i = 0; i < size; i++) {
            var x = left + i;
            res[i] = (int) Math.max(x / n, x % n) + 1;
        }
        return res;
    }
}