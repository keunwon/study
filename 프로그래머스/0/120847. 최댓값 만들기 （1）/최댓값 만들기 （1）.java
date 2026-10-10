class Solution {
    public int solution(int[] numbers) {
        var n = numbers.length;
        var one = Math.min(numbers[0], numbers[1]);
        var two = Math.max(numbers[0], numbers[1]);

        for (var i = 2; i < n; i++) {
            var num = numbers[i];
            if (num > two) {
                one = two;
                two = num;
            } else if (num > one) {
                one = num;
            }
        }
        return one * two;
    }
}