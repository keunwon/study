import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers) {
        var n = numbers.length;
        var res = new int[n];
        var stk = new int[n];
        var top = -1;

        Arrays.fill(res, -1);
        
        for (var i = 0; i < n; i++) {
            var num = numbers[i];
            while (top > -1 && num > numbers[stk[top]]) {
                var j = stk[top--];
                res[j] = num;
            }
            stk[++top] = i;
        }
        return res;
    }
}