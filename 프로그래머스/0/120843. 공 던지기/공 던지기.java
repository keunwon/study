class Solution {
    public int solution(int[] numbers, int k) {
        var idx = ((k - 1) * 2 % numbers.length);
        return numbers[idx];
    }
}