package com.keunwon.algorithm.programmers

/**
 * Title: 푸드 파이트 대회
 * Level: 1
 **/
class Lessons134240 {
    fun solution(food: IntArray): String {
        val sb = StringBuilder()
        for ((i, num) in food.withIndex()) {
            if (i == 0) continue
            val tmp = "$i".repeat(num / 2)
            sb.append(tmp)
        }
        return sb.toString() + "0" + sb.toString().reversed()
    }
}
