package com.keunwon.algorithm.programmers

class Lesson77486 {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val parents = enroll.zip(referral).toMap()
        val users = enroll.associateWith { 0 }.toMutableMap()

        for ((i, sell) in seller.withIndex()) {
            var cur = sell
            var price = amount[i] * 100

            while (price > 0 && cur != "-") {
                users[cur] = users.getValue(cur) + price - price / 10
                cur = parents.getValue(cur)
                price /= 10
            }
        }
        return enroll.map { users.getValue(it) }.toIntArray()
    }
}
