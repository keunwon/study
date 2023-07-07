package com.keunwon.algorithm.programmers

import kotlin.math.abs

/**
 * Title: 키패드 누르기
 * Level: 1
 **/
class Lessons67256 {
    fun solution(numbers: IntArray, hand: String): String {
        var left = 10
        var right = 12
        val answer = StringBuilder()

        for (number in numbers) {
            if (number in intArrayOf(1, 4, 7)) {
                left = number
                answer.append("L")
                continue
            } else if (number in intArrayOf(3, 6, 9)) {
                right = number
                answer.append("R")
                continue
            }

            val (leftDistance, rightDistance) = run {
                val (y, x) = getPosition(number)
                val (ly, lx) = getPosition(left)
                val (ry, rx) = getPosition(right)
                abs(ly - y) + abs(lx - x) to abs(ry - y) + abs(rx - x)
            }

            if (leftDistance == rightDistance) {
                if (hand == "left") {
                    left = number
                    answer.append("L")
                } else if (hand == "right") {
                    right = number
                    answer.append("R")
                }
            } else if (leftDistance < rightDistance) {
                left = number
                answer.append("L")
            } else if (leftDistance > rightDistance) {
                right = number
                answer.append("R")
            }
        }
        return answer.toString()
    }

    private fun getPosition(n: Int): Pair<Int, Int> {
        val num = if (n == 0) 10 else n - 1
        return num / 3 to num % 3
    }
}
