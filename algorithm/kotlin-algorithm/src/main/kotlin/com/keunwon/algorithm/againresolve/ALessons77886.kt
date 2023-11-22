package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons77886 {
    fun solution(s: Array<String>): Array<String> = s.map { transfer(it) }.toTypedArray()

    private fun transfer(text: String): String {
        val stack = Stack<Char>()
        var count = 0
        for (c in text) {
            stack.push(c)
            if (stack.size < 3) continue

            val str = Array(3) { stack.pop() }.joinToString("").reversed()
            if (str == "110") ++count else str.forEach { stack.push(it) }
        }

        val sb = StringBuilder()
        var index = stack.size
        var flag = false

        while (stack.isNotEmpty()) {
            if (stack.peek() == '0') flag = true
            else if (stack.peek() == '1' && !flag) --index
            sb.insert(0, stack.pop())
        }
        repeat(count) { sb.insert(index, "110") }
        return sb.toString()
    }
}

fun main() {
    ALessons77886().solution(
        arrayOf("1110", "100111100", "0111111010")
    ).also { println(it.contentToString()) }
}
