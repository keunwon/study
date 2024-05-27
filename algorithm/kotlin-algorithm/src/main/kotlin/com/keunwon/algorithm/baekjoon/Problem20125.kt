package com.keunwon.algorithm.baekjoon

class Problem20125 {
    private lateinit var map: Array<CharArray>

    fun solution(map: Array<CharArray>): Array<IntArray> {
        this.map = map
        val heart = getHeart()
        val leftArm = getDistance(0, Pair(heart.first, heart.second - 1))
        val rightArm = getDistance(1, Pair(heart.first, heart.second + 1))
        val waist = getDistance(2, Pair(heart.first + 1, heart.second))
        val leftLeg = getDistance(2, Pair(heart.first + waist + 1, heart.second - 1))
        val rightLeg = getDistance(2, Pair(heart.first + waist + 1, heart.second + 1))

        return arrayOf(
            intArrayOf(heart.first + 1, heart.second + 1),
            intArrayOf(leftArm, rightArm, waist, leftLeg, rightLeg)
        )
    }

    private fun getDistance(dir: Int, start: Pair<Int, Int>): Int {
        var (r, c) = start
        var count = 1

        while (true) {
            r += moves[dir].first
            c += moves[dir].second

            if (r !in map.indices || c !in map[0].indices) break
            if (map[r][c] != '*') break

            ++count
        }
        return count
    }

    private fun getHeart(): Pair<Int, Int> {
        for (i in map.indices) {
            for ((j, type) in map[i].withIndex()) {
                if (type == '*') return Pair(i + 1, j)
            }
        }
        error("not found heart")
    }

    companion object {
        private val moves = arrayOf(0 to -1, 0 to 1, 1 to 0)
    }
}

fun main() {
    val n = readln().toInt()
    val map = Array(n) { readln().toCharArray() }

    Problem20125().solution(map).forEach { println(it.joinToString(" ")) }
}
