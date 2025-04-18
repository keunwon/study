package algorithm.programmers

import kotlin.math.abs

class Lesson67256 {
    fun solution(numbers: IntArray, hand: String): String {
        val result = StringBuilder()
        var left = 10
        var right = 12

        for (n in numbers) {
            when (n) {
                in intArrayOf(1, 4, 7) -> {
                    left = n
                    result.append("L")
                }

                in intArrayOf(3, 6, 9) -> {
                    right = n
                    result.append("R")
                }

                in intArrayOf(2, 5, 8, 0) -> {
                    val t = if (n == 0) 11 else n
                    val diff1 = abs(t - left) / 3 + abs(t - left) % 3
                    val diff2 = abs(t - right) / 3 + abs(t - right) % 3

                    if (diff1 == diff2) {
                        if (hand == "left") {
                            result.append("L")
                            left = t
                        } else if (hand == "right") {
                            result.append("R")
                            right = t
                        }
                    } else if (diff1 < diff2) {
                        left = t
                        result.append("L")
                    } else {
                        right = t
                        result.append("R")
                    }
                }
            }
        }
        return result.toString()
    }
}
