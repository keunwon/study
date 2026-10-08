class Solution {
    public double solution(int[] numbers) {
        var total = 0;
        for (var num : numbers) total += num;
        return (double) total / numbers.length;
    }
}