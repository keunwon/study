class Solution {
    public int[] solution(long n) {
        var m = (int) Math.log10(n) + 1;
        var res = new int[m];
        var rIdx = 0;

        while (rIdx < m) {
            res[rIdx++] = (int) (n % 10);
            n /= 10;
        }
        return res;
    }
}