class Solution {
    public int[] solution(int[] numbers) {
        for (var i = 0; i < numbers.length; i++) {
            numbers[i] *= 2;
        }
        return numbers;
    }
}