package algorithm.leetcode

import java.util.Stack

class `682_Baseball_Game` {
    fun calPoints(operations: Array<String>): Int {
        val stack = Stack<Int>()
        operations.forEach { op ->
            when (op) {
                "+" -> stack.push(stack[stack.lastIndex] + stack[stack.lastIndex - 1])
                "D" -> stack.push(stack.peek() * 2)
                "C" -> stack.pop()
                else -> stack.push(op.toInt())
            }
        }
        return stack.sum()
    }
}
