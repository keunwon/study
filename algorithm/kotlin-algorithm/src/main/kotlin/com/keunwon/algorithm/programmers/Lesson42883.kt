package com.keunwon.algorithm.programmers

class Lesson42883 {
    fun solution(number: String, k: Int): String {
        var lastIndex = 0
        val answer = StringBuilder()

        for (i in 0 until number.length - k) {
            var max = -1

            for (j in lastIndex..i + k) {
                val num = number[j] - '0'
                if (max < num) {
                    max = num
                    lastIndex = j + 1
                }
            }
            answer.append(max)
        }
        return answer.toString()
    }
}
