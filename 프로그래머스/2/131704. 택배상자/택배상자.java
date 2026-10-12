import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        var stk = new Stack<Integer>();
        var oIdx = 0;

        for (var i = 1; i <= order.length; i++) {
            stk.add(i);
            
            while (!stk.isEmpty() && order[oIdx] == stk.peek()) {
                stk.pop();
                ++oIdx;
            }
        }
        return order.length - stk.size();
    }
}