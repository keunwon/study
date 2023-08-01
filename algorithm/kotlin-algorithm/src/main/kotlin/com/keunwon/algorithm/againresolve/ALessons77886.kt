package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons77886 {
    fun solution(s: Array<String>): Array<String> {
        return s.map(::transfer).toTypedArray()
    }

    private fun transfer(binary: String): String {
        val stack = Stack<Char>()
        var count = 0

        for (c in binary) {
            stack.push(c)

            while (stack.size >= 3) {
                val tmp = stack.takeLast(3).joinToString("")
                if (tmp != "110") break
                repeat(3) { stack.pop() }
                count++
            }
        }

        val sb = StringBuilder().apply { append(stack.joinToString("")) }
        val index = stack.lastIndexOf('0') + 1
        sb.insert(index, "110".repeat(count))
        return sb.toString()
    }
}

fun main() {
    val s = arrayOf("1110", "100111100", "0111111010")
    ALessons77886().solution(s).forEach { println(it) }
}
