class Solution {
    private final int[] rates = new int[] {10, 20, 30, 40};
    private int[][] users;
    private int[] emoticons;

    private int bestJoin = 0;
    private int besetPrice = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;

        dfs(new int[emoticons.length], 0);
        return new int[] {bestJoin, besetPrice};
    }

    private void dfs(int[] pick, int depth) {
        if (depth == pick.length) {
            var join = 0;
            var price = 0;

            for (var user : users) {
                var userRate = user[0];
                var userMaxPrice = user[1];
                var totalPrice = 0;

                for (var i = 0; i < emoticons.length; i++) {
                    var en = emoticons[i];
                    var rate = pick[i];

                    if (rate >= userRate) {
                        totalPrice += (en * (100 - rate) / 100);
                    }
                }

                if (totalPrice >= userMaxPrice) ++join;
                else price += totalPrice;
            }

            if (join > bestJoin || (join == bestJoin && price > besetPrice)) {
                bestJoin = join;
                besetPrice = price;
            }
            return;
        }

        for (var r : rates) {
            pick[depth] = r;
            dfs(pick, depth + 1);
        }
    }
}