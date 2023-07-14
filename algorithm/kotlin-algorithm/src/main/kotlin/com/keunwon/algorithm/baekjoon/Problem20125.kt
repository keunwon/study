package com.keunwon.algorithm.baekjoon

/**
 * Title: 쿠키의 신체 측정
 * Level: 실버-4
 **/
class Problem20125 {
    private val moves = arrayOf(0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<String>): Array<IntArray> {
        val hear = findHeart(map)
        val leftArm = getDistance(2, Position(hear.x, hear.y - 1), map)
        val rightArm = getDistance(0, Position(hear.x, hear.y + 1), map)
        val waist = getDistance(1, Position(hear.x + 1, hear.y), map)
        val leftLeg = getDistance(1, Position(hear.x + waist + 1, hear.y - 1), map)
        val rightLeg = getDistance(1, Position(hear.x + waist + 1, hear.y + 1), map)

        return arrayOf(
            intArrayOf(hear.x + 1, hear.y + 1),
            intArrayOf(leftArm, rightArm, waist, leftLeg, rightLeg),
        )
    }

    private fun getDistance(dir: Int, src: Position, map: Array<String>): Int {
        val (r, c) = moves[dir]
        var cur = src.copy()
        var count = 0

        while (map[cur.x][cur.y] == '*') {
            count++

            val nr = cur.x + r
            val nc = cur.y + c

            if (nr !in map.indices || nc !in map[0].indices) break
            cur = Position(nr, nc)
        }
        return count
    }

    private fun findHeart(map: Array<String>): Position {
        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j] == '_') continue
                if (i - 1 < 0 || j - 1 < 0 || i + 1 > map.lastIndex || j + 1 > map.lastIndex) continue

                val tmp = charArrayOf(
                    map[i - 1][j],
                    map[i][j - 1],
                    map[i][j + 1],
                    map[i + 1][j],
                )
                if (tmp.all { it == '*' }) return Position(i, j)
            }
        }
        error("심장 위치를 찾지 못했습니다.")
    }

    private data class Position(val x: Int, val y: Int)
}

fun main() {
    val n = readLine()!!.toInt()
    val map = Array(n) { readLine()!! }

    Problem20125().solution(map).forEach { println(it.joinToString(" ")) }
}
