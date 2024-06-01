package com.keunwon.algorithm.programmers

class Lesson150368 {
    private lateinit var users: Array<IntArray>
    private lateinit var emoticons: IntArray

    private val answer = intArrayOf(0, 0)

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        this.users = users
        this.emoticons = emoticons

        dfs(0, IntArray(emoticons.size))
        return answer
    }

    private fun dfs(depth: Int, rates: IntArray) {
        if (depth == rates.size) {
            var join = 0
            var cost = 0

            for ((minRate, maxPrice) in users) {
                val price = rates.zip(emoticons).sumOf { (r, e) ->
                    if (minRate <= r) e * (100 - r) / 100 else 0
                }
                if (maxPrice <= price) ++join else cost += price
            }

            if (answer[0] < join || (answer[0] == join && answer[1] < cost)) {
                answer[0] = join
                answer[1] = cost
            }
            return
        }

        for (rate in intArrayOf(10, 20, 30, 40)) {
            rates[depth] = rate
            dfs(depth + 1, rates)
        }
    }
}