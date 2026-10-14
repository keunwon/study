class Solution {
    public long[] solution(long[] numbers) {
        var res = new long[numbers.length];
        for (var i = 0; i < numbers.length; i++) {
            var num = numbers[i];
            if ((num & 1) == 0) {
                res[i] = num + 1;
            } else {
                var lowestZero = (~num) & (num + 1);
                res[i] = num + lowestZero - (lowestZero >> 1);
            }
        }
        return res;
    }
}