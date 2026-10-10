class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        var rank = new int[] {6, 6, 5, 4, 3, 2, 1};
        var zeroCount = 0;
        var matchCount = 0;

        for (var lotto : lottos) {
            if (lotto == 0) {
                ++zeroCount;
                continue;
            }

            for (var winNum : win_nums) {
                if (lotto == winNum) {
                    ++matchCount;
                    break;
                }
            }
        }
        return new int[] {rank[matchCount + zeroCount], rank[matchCount]};
    }
}