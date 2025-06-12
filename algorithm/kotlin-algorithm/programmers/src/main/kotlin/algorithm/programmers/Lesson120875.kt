package algorithm.programmers

import kotlin.math.abs

class Lesson120875 {
    fun solution(dots: Array<IntArray>): Int {
        return if (
            inclination(dots[0], dots[1]) == inclination(dots[2], dots[3]) ||
            inclination(dots[0], dots[2]) == inclination(dots[1], dots[3]) ||
            inclination(dots[0], dots[3]) == inclination(dots[1], dots[2])
        ) {
            1
        } else {
            0
        }
    }

    // 기울기 구하기
    private fun inclination(p1: IntArray, p2: IntArray): Double {
        val (x1, y1) = p1
        val (x2, y2) = p2
        return abs(x1 - x2).toDouble() / abs(y1 - y2)
    }
}
