package com.keunwon.algorithm.againresolve

class ALessons77486 {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val members = enroll.associateWith { 0 }.toMutableMap()
        val parent = enroll.zip(referral).toMap()

        for ((i, sell) in seller.withIndex()) {
            var name = sell
            var price = amount[i] * 100

            while (name != "-" && price > 0) {
                members[name] = members.getValue(name) + price - price / 10
                name = parent.getValue(name)
                price /= 10
            }
        }
        return enroll.map { members.getValue(it) }.toIntArray()
    }
}

fun main() {
    val enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
    val referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
    val seller = arrayOf("young", "john", "tod", "emily", "mary")
    val amount = intArrayOf(12, 4, 2, 5, 10)

    ALessons77486().solution(
        enroll,
        referral,
        seller,
        amount,
    ).also { println(it.joinToString(", ")) }
}
