package algorithm.programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Lesson1845 {
    public int solution(int[] nums) {
        var max = nums.length / 2;
        var set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return Math.min(max, set.size());
    }
}
