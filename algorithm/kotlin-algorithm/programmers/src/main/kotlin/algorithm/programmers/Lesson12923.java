package algorithm.programmers;

import java.util.ArrayList;

public class Lesson12923 {
    public int[] solution(long begin, long end) {
        var answer = new ArrayList<Integer>();
        for (var i = begin; i <= end; i++) {
            answer.add(max((int) i));
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int max(int n) {
        if (n == 1) return 0;

        var result = 1;
        for (var i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0L) {
                var div = n / i;

                if (div <= 10_000_000) {
                    result = div;
                    break;
                } else {
                    result = i;
                }
            }
        }
        return result;
    }
}
