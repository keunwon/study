package algorithm.programmers

import kotlin.math.ceil

class Lesson42586 {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val numbers = progresses.zip(speeds).map {
            ceil((100 - it.first).toDouble() / it.second).toInt()
        }
        val answer = mutableListOf<Int>()
        var max = numbers[0]
        var step = 1

        for (i in 1 until numbers.size) {
            if (max < numbers[i]) {
                answer.add(step)
                max = numbers[i]
                step = 1
            } else {
                ++step
            }
        }
        answer.add(step)
        return answer.toIntArray()
    }
}
