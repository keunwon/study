package algorithm.leetcode

import java.util.Stack

class `150_Evaluate_Reverse_Polish_Notation` {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = Stack<Int>()

        for (token in tokens) {
            when (token) {
                "+" -> stack.push(stack.pop() + stack.pop())
                "*" -> stack.push(stack.pop() * stack.pop())

                "-" -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(a - b)
                }

                "/" -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(a / b)
                }

                else -> stack.push(token.toInt())
            }
        }
        return stack.pop()
    }
}
