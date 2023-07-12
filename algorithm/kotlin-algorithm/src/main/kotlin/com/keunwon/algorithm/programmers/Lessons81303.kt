package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 표 편집
 * Level: 3
 **/
class Lessons81303 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var current = k
        var tableSize = n
        val undo = Stack<Int>()

        for (s in cmd) {
            val (option, number) = s.split(" ").let {
                it[0] to if (it.size == 2) it[1].toInt() else 0
            }

            when (option) {
                "U" -> current -= number
                "D" -> current += number
                "C" -> {
                    undo.push(current)
                    tableSize--
                    if (current == tableSize) current--
                }
                "Z" -> {
                    val undoNum = undo.pop()
                    if (undoNum <= current) current++
                    tableSize++
                }
            }
        }

        val sb = StringBuilder().apply {
            append("O".repeat(tableSize))
            while (undo.isNotEmpty()) insert(undo.pop(), "X")
        }
        return sb.toString()
    }
}
