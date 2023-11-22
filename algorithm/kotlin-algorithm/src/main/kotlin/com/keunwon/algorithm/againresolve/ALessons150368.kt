package com.keunwon.algorithm.againresolve

class ALessons150368 {
    private val discountRates = intArrayOf(10, 20, 30, 40)
    private val answer = mutableListOf<Pair<Int, Int>>()

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        dfs(0, IntArray(emoticons.size), users, emoticons)
        return answer.sortedWith(compareBy({ -it.first }, { -it.second }))
            .first()
            .let { (a, b) -> intArrayOf(a, b) }
    }

    private fun dfs(
        depth: Int,
        rates: IntArray,
        users: Array<IntArray>,
        emoticons: IntArray,
    ) {
        if (depth == rates.size) {
            var join = 0
            var fee = 0

            for ((leastRate, price) in users) {
                val tmpFee = rates.zip(emoticons).filter { leastRate <= it.first }
                    .sumOf { (r, d) -> d * (100 - r) / 100 }

                if (price <= tmpFee) ++join
                else fee += tmpFee
            }
            answer.add(join to fee)
            return
        }

        discountRates.forEach {
            rates[depth] = it
            dfs(depth + 1, rates, users, emoticons)
        }
    }
}

fun main() {
    ALessons150368().solution(
        arrayOf(
            intArrayOf(40, 10000), intArrayOf(25, 10000)
        ),
        intArrayOf(7000, 9000)
    ).also { println(it.contentToString()) }

    ALessons150368().solution(
        arrayOf(
            intArrayOf(40, 2900),
            intArrayOf(23, 10000),
            intArrayOf(11, 5200),
            intArrayOf(5, 5900),
            intArrayOf(40, 3100),
            intArrayOf(27, 9200),
            intArrayOf(32, 6900)
        ),
        intArrayOf(1300, 1500, 1600, 4900)
    ).also { println(it.contentToString()) }
}
