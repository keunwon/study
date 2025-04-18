package algorithm.programmers;

import java.util.ArrayList;

public class Lesson12906 {
    public int[] solution(int[] arr) {
        var result = new ArrayList<Integer>();
        var pre = -1;

        for (var n : arr) {
            if (pre != n) {
                result.add(n);
                pre = n;
            }
        }

        return result.stream().mapToInt(v -> v).toArray();
    }
}
