import java.util.Arrays;

class Solution {
    public int[] solution(int[][] score) {
        var n = score.length;
        var res = new int[n];

        Arrays.fill(res, 1);

        for (var i = 0; i < n; i++) {
            var t1 = score[i][0] + score[i][1];
            for (var j = 0; j < n; j++) {
                if (score[j][0] + score[j][1] > t1) {
                    ++res[i];
                }
            }
        }
        return res;
    }
}