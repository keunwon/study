package com.keunwon.algorithm.programmers

/**
 * Title: 이진 변환 반복하기
 * Level: 2
 **/
class Lessons70129 {
    fun solution(s: String): IntArray {
        val answer = IntArray(2)
        var binary = s

        while (binary != "1") {
            answer[0]++
            answer[1] += binary.count { it == '0' }
            binary = binary.count { it == '1' }.toString(2)
        }
        return answer
    }
}
