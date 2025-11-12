package algorithm.programmers;

import java.util.Arrays;

public class Lesson12907 {
    public int solution(int n, int[] money) {
        Arrays.sort(money);

        var dp = new int[n + 1];
        dp[0] = 1;

        for (var i = 0; i < money.length; i++) {
            for (var j = money[i]; j <= n; j++) {
                dp[j] += (dp[j - money[i]]) % 1_000_000_007;
            }
        }

        return dp[n];
    }
}
