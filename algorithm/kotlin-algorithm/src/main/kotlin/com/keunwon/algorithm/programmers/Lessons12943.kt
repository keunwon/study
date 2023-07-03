package com.keunwon.algorithm.programmers

/**
 * Title: 콜라츠 추측
 * Level: 1
 **/
class Lessons12943 {
    fun solution(num: Int): Int {
        var n = num.toLong()
        var answer = 0

        while (n != 1L) {
            if (answer > 500) return -1
           
            if (n % 2 == 0L) n /= 2
            else n = n * 3 + 1
            ++answer
        }
        return answer
    }
}
