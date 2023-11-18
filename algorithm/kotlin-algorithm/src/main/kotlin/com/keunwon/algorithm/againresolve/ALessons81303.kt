package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons81303 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var tableSize = n
        var curIndex = k
        val undo = Stack<Int>()

        for (i in cmd.indices) {
            val (option, number) = cmd[i].split(" ").let {
                val second = if (it.size == 1) 0 else it[1].toInt()
                it[0] to second
            }

            when (option) {
                "U" -> curIndex -= number
                "D" -> curIndex += number
                "C" -> {
                    undo.push(curIndex)
                    --tableSize
                    if (curIndex == tableSize) --curIndex
                }
                "Z" -> {
                    if (undo.pop() <= curIndex) ++curIndex
                    ++tableSize
                }
            }
        }

        val sb = StringBuilder("O".repeat(tableSize))
        while (undo.isNotEmpty()) {
            sb.insert(undo.pop(), "X")
        }
        return sb.toString()
    }
}

fun main() {
    ALessons81303().solution(
        8,
        2,
        arrayOf("D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z")
    ).also(::println)

    ALessons81303().solution(
        8,
        2,
        arrayOf("D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C")
    ).also(::println)
}
