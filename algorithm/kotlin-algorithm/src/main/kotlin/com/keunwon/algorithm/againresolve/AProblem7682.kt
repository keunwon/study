package com.keunwon.algorithm.againresolve

class AProblem7682 {
    fun solution(line: String): String {
        val xCount = line.count { it == 'X' }
        val oCount = line.count { it == 'O' }
        val map = line.chunked(3)

        return when (xCount) {
            oCount -> {
                if (!check(map, 'X') && check(map, 'O')) VALID
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

    private fun check(map: List<String>, target: Char): Boolean {
        for (arr in map) {
            if (arr.all { it == target }) return true
        }
        for (i in map.indices) {
            var count = 0
            for (j in map[0].indices) {
                if (map[j][i] == target) count++
            }
            if (count == 3) return true
        }
        return charArrayOf(map[0][0], map[1][1], map[2][2]).all { it == target } ||
                charArrayOf(map[0][2], map[1][1], map[2][0]).all { it == target }
    }

    companion object {
        private const val INVALID = "invalid"
        private const val VALID = "valid"
    }
}

fun main() {
    while (true) {
        val line = readLine()!!
        if (line == "end") break
        AProblem7682().solution(line).also { println(it) }
    }
}
