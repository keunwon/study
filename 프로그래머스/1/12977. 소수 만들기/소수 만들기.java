import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public int solution(int[] nums) {
        var primes = new boolean[3001];

        primes[0] = false;
        primes[1] = false;
        Arrays.fill(primes, true);

        for (var i = 2; i <= Math.sqrt(3000); i++) {
            for (var j = i + i; j <= 3000; j += i) {
                primes[j] = false;
            }
        }

        var n = nums.length;
        var count = 0;

        for (var i = 0; i < n; i++) {
            for (var j = i + 1; j < n; j++) {
                for (var k = j + 1; k < n; k++) {
                    var sum = nums[i] + nums[j] + nums[k];
                    if (primes[sum]) ++count;
                }
            }
        }
        return count;
    }
}