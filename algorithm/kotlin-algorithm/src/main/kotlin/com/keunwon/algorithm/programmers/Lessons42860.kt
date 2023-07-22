package com.keunwon.algorithm.programmers

/**
 * Title: 조이스틱
 * Level: 2
 **/
class Lessons42860 {
    fun solution(name: String): Int {
        var change = 0
        var move = name.lastIndex

        for (i in name.indices) {
            change += changeCount(name[i])

            var tmpIndex = i + 1
            while (tmpIndex < name.length && name[tmpIndex] == 'A') {
                tmpIndex++
            }

            val diff = name.length - tmpIndex
            move = move
                .coerceAtMost(i * 2 + diff)
                .coerceAtMost(diff * 2 + i)
        }
        return change + move
    }

    private fun changeCount(c: Char): Int {
        val count1 = c - 'A'
        val count2 = 'Z' - c + 1
        return count1.coerceAtMost(count2)
    }
}
