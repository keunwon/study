package algorithm.leetcode

import java.util.Stack

class `20_Valid_Parentheses` {
    fun isValid(s: String): Boolean {
        val map = mapOf(
            '(' to ')',
            '[' to ']',
            '{' to '}',
        )
        val stack = Stack<Char>()

        for (c in s) {
            if (map.containsKey(c)) {
                stack.push(c)
                continue
            }

            if (stack.isEmpty() || c != map.getValue(stack.peek())) return false
            stack.pop()
        }
        return stack.isEmpty()
    }
}
