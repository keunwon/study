package algorithm.programmers;

public class Lesson43105 {
    public int solution(int[][] triangle) {
        var dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (j == triangle[i].length - 1) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
                dp[i][j] += triangle[i][j];
            }
        }

        var answer = dp[dp.length - 1][0];
        for (int i = 1; i < dp[0].length; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }
        return answer;
    }
}
