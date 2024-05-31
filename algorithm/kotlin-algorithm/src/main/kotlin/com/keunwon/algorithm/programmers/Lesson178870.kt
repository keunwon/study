package com.keunwon.algorithm.programmers

class Lesson178870 {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var sum = 0
        var sIndex = 0
        val answer = intArrayOf(0, sequence.lastIndex)

        for ((i, num) in sequence.withIndex()) {
            sum += num

            while (sum > k) {
                sum -= sequence[sIndex]
                sIndex++
            }

            if (sum == k && answer[1] - answer[0] > i - sIndex) {
                answer[0] = sIndex
                answer[1] = i
            }
        }
        return answer
    }
}
