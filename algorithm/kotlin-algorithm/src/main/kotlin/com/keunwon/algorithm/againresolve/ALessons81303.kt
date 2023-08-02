package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons81303 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val undo = Stack<Int>()
        var cursor = k
        var tableSize = n

        for (str in cmd) {
            val st = StringTokenizer(str)

            when (st.nextToken()) {
                "U" -> {
                    val x = st.nextToken().toInt()
                    cursor -= x
                }
                "D" -> {
                    val x = st.nextToken().toInt()
                    cursor += x
                }
                "C" -> {
                    undo.push(cursor)
                    tableSize--
                    if (tableSize == cursor) cursor--
                }
                "Z" -> {
                    val tmp = undo.pop()
                    if (tmp <= cursor) cursor++
                    tableSize++
                }
            }
        }

        val answer = StringBuilder()
        answer.append("O".repeat(tableSize))
        while (undo.isNotEmpty()) answer.insert(undo.pop(), 'X')
        return answer.toString()
    }
}

fun main() {
    val n = 8
    val k = 2
    val cmd = arrayOf("D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z")

    ALessons81303().solution(n, k, cmd).also { println(it) }
}
