package algorithm.programmers;

import java.util.Stack;

public class Lesson12909 {
    public boolean solution(String s) {
        var stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) return false;
            stack.pop();
        }
        return stack.isEmpty();
    }
}
