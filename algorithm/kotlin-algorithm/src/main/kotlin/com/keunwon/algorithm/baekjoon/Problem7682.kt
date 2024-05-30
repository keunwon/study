package com.keunwon.algorithm.baekjoon

class Problem7682 {
    fun solution(text: String): String {
        val map = text.chunked(3).toTypedArray()
        val xCount = text.count { it == 'X' }
        val oCount = text.count { it == 'O' }

        return when (xCount) {
            oCount -> {
                if (check(map, 'X') && check(map, 'O')) VALID
                else INVALID
            }

            oCount + 1 -> {
                if (xCount + oCount == 9 && !check(map, 'O')) VALID
                else if (check(map, 'X') && !check(map, 'O')) VALID
                else INVALID
            }

            else -> INVALID
        }
    }

    private fun check(map: Array<String>, target: Char): Boolean {
        for (i in map.indices) {
            if (map[i].all { it == target }) return true
        }

        for (i in map[0].indices) {
            var count = 0
            for (j in map.indices) {
                if (map[j][i] == target) ++count
            }
            if (count == map.size) return true
        }
        return charArrayOf(map[0][0], map[1][1], map[2][2]).all { it == target } ||
                charArrayOf(map[0][2], map[1][1], map[2][0]).all { it == target }
    }

    companion object {
        private const val VALID = "valid"
        private const val INVALID = "invalid"
    }
}

fun main() {
    while (true) {
        val text = readln()
        if (text == "end") break

        println(Problem7682().solution(text))
    }
}
