import java.util.Arrays;
import java.util.ArrayDeque;

class Solution {
    public int[] solution(int[] numbers) {
        var n = numbers.length;
        var res = new int[n];
        var stk = new ArrayDeque<Integer>();
        
        Arrays.fill(res, -1);

        for (var i = 0; i < n; i++) {
            while (!stk.isEmpty() && numbers[i] > numbers[stk.getLast()]) {
                var j = stk.removeLast();
                res[j] = numbers[i];
            }
            stk.addLast(i);
        }
        return res;
    }
}