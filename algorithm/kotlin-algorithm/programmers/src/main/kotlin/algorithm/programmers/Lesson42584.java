package algorithm.programmers;

import java.util.Stack;

public class Lesson42584 {
    public int[] solution(int[] prices) {
        var answer = new int[prices.length];
        var stack = new Stack<Integer>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                var sIndex = stack.pop();
                answer[sIndex] = i - sIndex;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            var sIndex = stack.pop();
            answer[sIndex] = prices.length - sIndex - 1;
        }
        return answer;
    }
}
