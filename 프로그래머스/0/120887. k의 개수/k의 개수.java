class Solution {
    public int solution(int i, int j, int k) {
        var count = 0;
        for (var n = i; n <= j; n++) {
            var cur = n;
            while (cur > 0) {
                if (cur % 10 == k) ++count;
                cur /= 10;
            }
        }
        return count;
    }
}