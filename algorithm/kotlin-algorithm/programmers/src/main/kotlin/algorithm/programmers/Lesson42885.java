package algorithm.programmers;

import java.util.Arrays;

public class Lesson42885 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        var answer = 0;
        var left = 0;
        var right = people.length - 1;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                ++left;
            }
            --right;
            ++answer;
        }
        return answer;
    }
}
