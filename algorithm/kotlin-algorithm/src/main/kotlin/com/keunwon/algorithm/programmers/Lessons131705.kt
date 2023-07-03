package com.keunwon.algorithm.programmers

/**
 * Title: 삼총사
 * Level: 1
 **/
class Lessons131705 {
    fun solution(number: IntArray): Int {
        var answer = 0
        for (i in 0 until number.size - 2) {
            for (j in i + 1 until number.size - 1) {
                for (k in j + 1 until number.size) {
                    if (number[i] + number[j] + number[k] == 0) answer++
                }
            }
        }
        return answer
    }
}
