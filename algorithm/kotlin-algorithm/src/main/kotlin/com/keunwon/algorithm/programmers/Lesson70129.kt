package com.keunwon.algorithm.programmers

class Lesson70129 {
    fun solution(s: String): IntArray {
        var bianry = s
        val answer = intArrayOf(0, 0)

        while (bianry != "1") {
            val zeroCount = bianry.count { it == '0' }
            val length = bianry.length - zeroCount

            bianry = length.toString(2)
            answer[0]++
            answer[1] += zeroCount
        }
        return answer
    }
}
