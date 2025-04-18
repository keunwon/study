package algorithm.leetcode

import java.util.Stack

class `394_Decode_String` {
    fun decodeString(s: String): String {
        val numStack = Stack<Int>()
        val strStack = Stack<String>()
        var num = 0
        var str = ""

        for (c in s) {
            if (c == '[') {
                numStack.push(num)
                strStack.push(str)
                str = ""
                num = 0
            } else if (c == ']') {
                str = strStack.pop() + (str.repeat(numStack.pop()))
            } else {
                if (c.isDigit()) num = num * 10 + c.digitToInt()
                else str += c
            }
        }
        return str
    }
}

fun main() {
    `394_Decode_String`().decodeString("3[a]2[bc]").also { println(it) } // aaabcbc
    `394_Decode_String`().decodeString("3[a2[c]]").also { println(it) } // accaccacc
    `394_Decode_String`().decodeString("2[abc]3[cd]ef").also { println(it) } // abcabccdcdcdef
}
