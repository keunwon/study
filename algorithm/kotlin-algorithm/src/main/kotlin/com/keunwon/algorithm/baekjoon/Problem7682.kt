package com.keunwon.algorithm.baekjoon

/**
 * Title: 틱택토
 * Level: 골드-5
 **/
class Problem7682 {
    fun solution(line: String): String {
        val xCount = line.count { it == 'X' }
        val oCount = line.count { it == 'O' }
        val map = line.chunked(3)

        return when (xCount) {
            oCount + 1 -> {
                if (xCount + oCount == 9 && !check(map, 'O')) VALID
                else if (!check(map, 'O') && check(map, 'X')) VALID
                else INVALID
            }
            oCount -> {
                if (!check(map, 'X') && check(map, 'O')) VALID
                else INVALID
            }
            else -> INVALID
        }
    }

    private fun check(map: List<String>, target: Char): Boolean {
        for (i in map.indices) {
            if (map[i].all { it == target }) return true
        }

        for (i in map.indices) {
            var count = 0
            for (j in map[0].indices) {
                if (map[j][i] == target) count++
            }
            if (count == 3) return true
        }

        if (charArrayOf(map[0][0], map[1][1], map[2][2]).all { it == target }) return true
        if (charArrayOf(map[0][2], map[1][1], map[2][0]).all { it == target }) return true
        return false
    }

    companion object {
        private const val VALID = "valid"
        private const val INVALID = "invalid"
    }
}

fun main() {
    while (true) {
        val line = readLine()!!
        if (line == "end") break

        Problem7682().solution(line).also { println(it) }
    }
}
