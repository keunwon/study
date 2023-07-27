package com.keunwon.algorithm.againresolve

class AProblem3687 {
    fun solution(): List<Pair<String, String>> {
        val maxDp = Array(101) { "" }.apply {
            this[2] = "1"
            this[3] = "7"

            for (i in 4..100) this[i] = this[i - 2] + "1"
        }
        val minDp = LongArray(101) { Long.MAX_VALUE }.apply {
            this[2] = 1
            this[3] = 7
            this[4] = 4
            this[5] = 2
            this[6] = 6
            this[7] = 8
            this[8] = 10
        }
        val nums = intArrayOf(1, 7, 4, 2, 0, 8)

        for (i in 9..100) {
            for (j in 2..7) {
                val tmp = minDp[i - j] * 10 + nums[j - 2]
                minDp[i] = minDp[i].coerceAtMost(tmp)
            }
        }
        return minDp.map { "$it" }.zip(maxDp)
    }
}

fun main() {
    val dp = AProblem3687().solution()
    val t = readLine()!!.toInt()

    repeat(t) {
        val n = readLine()!!.toInt()
        dp[n].also { println("${it.first} ${it.second}") }
    }
}
