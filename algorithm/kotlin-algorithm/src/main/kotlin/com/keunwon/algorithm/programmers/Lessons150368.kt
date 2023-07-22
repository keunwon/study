package com.keunwon.algorithm.programmers

/**
 * Title: 이모티콘 할인행사
 * Level: 2
 **/
class Lessons150368 {
    private val discounts = intArrayOf(10, 20, 30, 40)
    private val answer = mutableListOf<Pair<Int, Int>>()

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        dfs(0, IntArray(emoticons.size), users, emoticons)
        return answer
            .sortedWith(compareBy({ -it.first }, { -it.second }))
            .first()
            .let { intArrayOf(it.first, it.second) }
    }

    private fun dfs(
        depth: Int,
        emoticonDiscounts: IntArray,
        users: Array<IntArray>,
        emoticons: IntArray,
    ) {
        if (depth == emoticonDiscounts.size) {
            var count = 0
            var cost = 0

            for ((discount, price) in users) {
                val sum = emoticonDiscounts.zip(emoticons).fold(0) { acc, (d, p) ->
                    val add = if (discount <= d) p * (100 - d) / 100 else 0
                    acc + add
                }
                if (price <= sum) count++
                else cost += sum
            }
            answer.add(count to cost)
            return
        }

        discounts.forEach { d ->
            emoticonDiscounts[depth] = d
            dfs(depth + 1, emoticonDiscounts, users, emoticons)
        }
    }
}
