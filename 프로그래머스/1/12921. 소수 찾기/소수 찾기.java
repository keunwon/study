import java.util.Arrays;

class Solution {
    public int solution(int n) {
        var primes = new boolean[n + 1];

        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (var i = 2; i <= Math.sqrt(n); i++) {
            for (var j = i + i; j <= n; j += i) {
                primes[j] = false;
            }
        }

        var count = 0;
        for (var i = 2; i <= n; i++) {
            if (primes[i]) ++count;
        }
        return count;
    }
}