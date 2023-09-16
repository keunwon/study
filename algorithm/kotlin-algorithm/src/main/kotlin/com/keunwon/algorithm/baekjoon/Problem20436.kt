package com.keunwon.algorithm.baekjoon

import kotlin.math.abs

/**
 * Title: ZOAC 3
 * Level: 실버-4
 **/
class Problem20436 {
    private val keyboard = arrayOf(
        charArrayOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
        charArrayOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        charArrayOf('z', 'x', 'c', 'v', 'b', 'n', 'm'),
    )

    fun solution(left: Char, right: Char, word: String): Int {
        var second = 0
        var leftKey = left
        var rightKey = right

        for (c in word) {
            if (isMoeum(c)) {
                second += getDistance(rightKey, c) + 1
                rightKey = c
            } else {
                second += getDistance(leftKey, c) + 1
                leftKey = c
            }
        }
        return second
    }

    private fun getDistance(key1: Char, key2: Char): Int {
        val (r1, c1) = getPosition(key1)
        val (r2, c2) = getPosition(key2)
        return abs(r2 - r1) + abs(c2 - c1)
    }

    private fun getPosition(target: Char): Pair<Int, Int> {
        for (i in keyboard.indices) {
            for ((j, key) in keyboard[i].withIndex()) {
                if (target == key) return i to j
            }
        }
        error("$target not found...")
    }

    private fun isMoeum(target: Char): Boolean = setOf(
        'y', 'u', 'i', 'o', 'p', 'h', 'j', 'k', 'l', 'b', 'n', 'm',
    ).contains(target)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (left, right) = br.readLine().split(" ").map { it[0] }
        val word = br.readLine()

        Problem20436().solution(left, right, word).also { bw.write("$it") }
        bw.flush()
    }
}
