class Solution {
    public int solution(int sticker[]) {
        switch (sticker.length) {
            case 1 -> {
                return sticker[0];
            }
            case 2 -> {
                return Math.max(sticker[0], sticker[1]);
            }
        }

        var n = sticker.length;
        return Math.max(maxOf(sticker, 0, n - 2), maxOf(sticker, 1, n - 1));
    }

    private int maxOf(int[] sticker, int start, int end) {
        var one = sticker[start];
        var two = Math.max(sticker[start], sticker[start + 1]);

        for (var i = start + 2; i <= end; i++) {
            var next = Math.max(one + sticker[i], two);
            one = two;
            two = next;
        }
        return two;
    }
}