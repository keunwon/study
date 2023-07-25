package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 110 옮기기
 * Level: 3
 **/
class Lessons77886 {
    fun solution(s: Array<String>): Array<String> {
        return s.map { replace(it) }.toTypedArray()
    }

    private fun replace(str: String): String {
        val stack = Stack<Char>()
        var count = 0

        for (c in str) {
            stack.push(c)
            if (stack.size < 3) continue

            val tmp = CharArray(3) { stack.pop() }.reversed().joinToString("")
            if (tmp == "110") {
                count++
                continue
            }
            tmp.forEach { stack.push(it) }
        }

        val sb = StringBuilder()
        var index = stack.size
        var flag = false

        while (stack.isNotEmpty()) {
            if (stack.peek() == '0') flag = true
            if (stack.peek() == '1' && !flag) index--
            sb.insert(0, stack.pop())
        }
        repeat(count) { sb.insert(index, "110") }
        return sb.toString()
    }
}
