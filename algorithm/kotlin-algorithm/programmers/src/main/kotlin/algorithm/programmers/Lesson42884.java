package algorithm.programmers;

import java.util.Arrays;
import java.util.Comparator;

public class Lesson42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));

        var end = routes[0][1];
        var answer = 1;

        for (int i = 1; i < routes.length; i++) {
            if (end < routes[i][0]) {
                end = routes[i][1];
                ++answer;
            }
        }
        return answer;
    }
}
