package algorithm.programmers

import kotlin.math.abs

class Lesson120860 {
    fun solution(dots: Array<IntArray>): Int {
        val target = dots[0]
        var x = 0
        var y = 0

        for (i in 1 until dots.size) {
            if (dots[i][0] == target[0]) {
                x = abs(dots[i][1] - target[1])
            } else {
                y = abs(dots[i][0] - target[0])
            }
        }

        return x * y
    }
}
