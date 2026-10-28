class Solution {
    public long solution(int r1, int r2) {
        var min = (double) r1 * r1;
        var max = (double) r2 * r2;
        var res = 0L;

        for (var c = 1; c <= r2; c++) {
            var x = (double) c * c;
            var n1 = (long) Math.ceil(Math.sqrt(min - x));
            var n2 = (long) Math.sqrt(max - x);
            res += n2 - n1 + 1;
        }
        return res * 4;
    }
}