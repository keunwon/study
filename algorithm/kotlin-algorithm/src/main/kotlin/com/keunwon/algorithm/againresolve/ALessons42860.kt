package com.keunwon.algorithm.againresolve

class ALessons42860 {
    fun solution(name: String): Int {
        var change = 0
        var move = name.lastIndex

        for ((i, c) in name.withIndex()) {
            change += moveCount(c)

            var tmpIndex = i + 1
            while (tmpIndex < name.length && name[tmpIndex] == 'A') {
                ++tmpIndex
            }

            val diff = name.length - tmpIndex
            move = move
                .coerceAtMost(i * 2 + diff)
                .coerceAtMost(diff * 2 + i)
        }
        return change + move
    }

    private fun moveCount(c: Char): Int = (c - 'A').coerceAtMost('Z' - c + 1)
}

fun main() {
    arrayOf("JEROEN", "JAN").forEach {
        ALessons42860().solution(it).also(::println)
    }
}
