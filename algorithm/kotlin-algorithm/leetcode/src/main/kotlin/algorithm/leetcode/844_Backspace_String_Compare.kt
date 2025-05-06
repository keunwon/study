package algorithm.leetcode

import java.util.Stack

class `844_Backspace_String_Compare` {
    fun backspaceCompare(s: String, t: String): Boolean {
        return s.removeBackspace() == t.removeBackspace()
    }

    private fun String.removeBackspace(): String {
        val stack = Stack<Char>()
        for (c in this) {
            if (c == '#') {
                if (stack.isNotEmpty()) stack.pop()
            } else {
                stack.push(c)
            }
        }
        return stack.joinToString("")
    }
}

fun main() {
    `844_Backspace_String_Compare`().backspaceCompare("y#fo##f", "y#f#o##f")
        .also { println(it) }
}
