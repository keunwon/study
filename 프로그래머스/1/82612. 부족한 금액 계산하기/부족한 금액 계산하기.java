class Solution {
    public long solution(int price, int money, int count) {
        var total = 0L;

        for (var i = 1; i <= count; i++) {
            total += ((long) price * i);
        }

        var diff = total - money;
        if (diff > 0) {
            return diff;
        }
        return 0L;
    }
}