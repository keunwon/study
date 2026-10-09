class Solution {
    public long solution(long n) {
        var count = new int[10];
        while (n > 0) {
            var pop = (int) (n % 10);
            ++count[pop];
            n /= 10;
        }

        var res = 0L;
        for (var i = 9; i >= 0; i--) {
            for (var j = 0; j < count[i]; j++) {
                res = res * 10 + i;
            }
        }
        return res;
    }
}