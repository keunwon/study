package com.keunwon.algorithm.againresolve

class ALessons42860 {
    fun solution(name: String): Int {
        val changeCount = name.sumOf { changeCount(it) }
        var moveCount = name.lastIndex

        for (i in name.indices) {
            var tmpIndex = i + 1
            while (tmpIndex < name.length && name[tmpIndex] == 'A') {
                tmpIndex++
            }

            val diff = name.length - tmpIndex
            moveCount = moveCount
                .coerceAtMost(i * 2 + diff)
                .coerceAtMost(diff * 2 + i)
        }
        return changeCount + moveCount
    }

    private fun changeCount(target: Char): Int {
        val count1 = target - 'A'
        val count2 = 'Z' - target + 1
        return count1.coerceAtMost(count2)
    }
}

fun main() {
    arrayOf("JEROEN", "JAN", "AAAAAAAABA").forEach { name ->
        ALessons42860().solution(name).also { println(it) }
    }
}
