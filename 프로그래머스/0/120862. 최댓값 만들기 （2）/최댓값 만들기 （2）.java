import java.util.Arrays;

class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        var n = numbers.length;
        return Math.max(numbers[0] * numbers[1], numbers[n - 2] * numbers[n - 1]);
    }
}