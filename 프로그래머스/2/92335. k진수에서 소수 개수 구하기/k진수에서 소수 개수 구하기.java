import java.util.Arrays;

class Solution {
    public int solution(int n, int k) {
        var list = Arrays.stream(Integer.toString(n, k).split("0"))
            	.filter(s -> !s.isBlank())
                .mapToLong(Long::parseLong)
                .toArray();
        var primeCount = 0;

        for (var num : list) {
            if (num < 2) continue;

            var isPrime = true;
            for (var i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0L) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) ++primeCount;
        }
        return primeCount;
    }
}