package com.keunwon.algorithm.againresolve

class ALessons77486 {
    fun solution(
        enroll: Array<String>,
        referral: Array<String>,
        seller: Array<String>,
        amount: IntArray,
    ): IntArray {
        val profitMembers = enroll.associateWith { 0 }.toMutableMap()
        val parents = enroll.zip(referral).toMap()

        for (i in seller.indices) {
            var cur = seller[i]
            var price = amount[i] * 100

            while (cur != "-" && price > 0) {
                profitMembers[cur] = profitMembers.getValue(cur) + (price - price / 10)
                cur = parents.getValue(cur)
                price /= 10
            }
        }
        return enroll.map { profitMembers.getValue(it) }.toIntArray()
    }
}

fun main() {
    ALessons77486().solution(
        arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"),
        arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"),
        arrayOf("young", "john", "tod", "emily", "mary"),
        intArrayOf(12, 4, 2, 5, 10)
    ).also { println(it.contentToString()) }

    ALessons77486().solution(
        arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"),
        arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"),
        arrayOf("sam", "emily", "jaimie", "edward"),
        intArrayOf(2, 3, 5, 4)
    ).also { println(it.contentToString()) }
}
