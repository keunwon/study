class Solution {
    public int solution(int[] money) {
        var len = money.length;
        return switch (len) {
            case 1 -> money[0];
            case 2 -> Math.max(money[0], money[1]);
            default -> Math.max(maxStolenMoney(money, 0, len - 2), maxStolenMoney(money, 1, len - 1));
        };
    }

    private int maxStolenMoney(int[] money, int startIndex, int endIncludeIndex) {
        var one = money[startIndex];
        var two = Math.max(money[startIndex], money[startIndex + 1]);

        for (var i = startIndex + 2; i <= endIncludeIndex; i++) {
            var next = Math.max(one + money[i], two);
            one = two;
            two = next;
        }
        return two;
    }
}