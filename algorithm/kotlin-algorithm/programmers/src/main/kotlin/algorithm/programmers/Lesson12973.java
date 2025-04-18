package algorithm.programmers;

import java.util.Stack;

public class Lesson12973 {
    public int solution(String s) {
        var stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            var target = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
            } else {
                stack.push(target);
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
