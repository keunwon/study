package algorithm.programmers;

import java.util.Arrays;

public class Lesson12938 {
    public int[] solution(int n, int s) {
        var div = s / n;
        var ret = s - (div * n);

        if (div == 0) return new int[]{-1};

        var answer = new int[n];

        Arrays.fill(answer, div);

        var index = 0;
        while (ret > 0) {
            ++answer[index % n];
            --ret;
            ++index;
        }
        Arrays.sort(answer);
        return answer;
    }
}
