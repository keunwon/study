class Solution {
    public long solution(int[] sequence) {
        var num1 = (long) sequence[0];
        var num2 = (long) -sequence[0];
        var maxRes = Math.max(num1, num2);
        var flag = true;

        for (var i = 1; i < sequence.length; i++) {
            var s = sequence[i];
            var t1 = flag ? -s : s;
            var t2 = flag ? s : -s;

            num1 = Math.max(num1 + t1, t1);
            num2 = Math.max(num2 + t2, t2);
            maxRes = Math.max(maxRes, Math.max(num1, num2));
            flag = !flag;
        }
        return maxRes;
    }
}