package com.keunwon.algorithm.programmers

/**
 * Title: 큰 수 만들기
 * Level: 2
 **/
class Lessons42883 {
    fun solution(number: String, k: Int): String {
        val answer = StringBuilder()
        var startIndex = 0

        for (i in 0 until number.length - k) {
            var max = 0

            for (j in startIndex..i + k) {
                if (max < number[j] - '0') {
                    max = number[j] - '0'
                    startIndex = j
                }
            }
            answer.append(max)
            startIndex++
        }
        return answer.toString()
    }
}
