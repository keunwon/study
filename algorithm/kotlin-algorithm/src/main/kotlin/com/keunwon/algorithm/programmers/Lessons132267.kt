package com.keunwon.algorithm.programmers

/**
 * Title: 콜라 문제
 * Level: 1
 **/
class Lessons132267 {
    fun solution(a: Int, b: Int, n: Int): Int {
        var answer = 0
        var tmp = n

        while (tmp / a > 0) {
            answer += (tmp / a) * b
            tmp = (tmp / a) * b + tmp % a
        }
        return answer
    }
}
