class Solution {
    public long solution(int k, int d) {
        var res = 0L;
        for (var x = 0; x <= d; x += k) {
            var tmp = ((long) d * d) - ((long) x * x);
            var sqrt = (int) Math.sqrt(tmp);
            res += sqrt / k + 1;
        }
        return res;
    }
}