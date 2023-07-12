package com.keunwon.algorithm.programmers

/**
 * Title: 다단계 칫솔 판매
 * Level: 3
 **/
class Lessons77486 {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val memberMap = enroll.associateWith { 0 }.toMutableMap()
        val parentMap = enroll.zip(referral).toMap()

        for ((i, sell) in seller.withIndex()) {
            var cur = sell
            var price = amount[i] * 100

            while (cur != "-" && price > 0) {
                memberMap[cur] = memberMap.getValue(cur) + price - price / 10
                cur = parentMap.getValue(cur)
                price /= 10
            }
        }
        return enroll.map { memberMap.getValue(it) }.toIntArray()
    }
}
