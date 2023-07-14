package com.keunwon.algorithm.baekjoon

/**
 * Title: KCPC
 * Level: 실버-3
 **/
class Problem3758 {
    fun solution(n: Int, k: Int, myId: Int, arr: Array<IntArray>): Int {
        val rankMap = mutableMapOf<Int, Rank>()

        for (i in arr.indices) {
            val (teamId, no, score) = arr[i]

            val rank = rankMap[teamId] ?: Rank(teamId, k)
            rankMap[teamId] = rank.apply {
                submitCount++
                scores[no] = scores[no].coerceAtLeast(score)
                lastTxId = i
            }
        }
        return rankMap.values.sortedWith(compareBy({ -it.sumByScore }, { it.submitCount }, { it.lastTxId }))
            .indexOfFirst { it.teamId == myId }
            .let { if (it == -1) n else it + 1 }
    }

    class Rank(
        val teamId: Int,
        var submitCount: Int,
        val scores: IntArray,
        var lastTxId: Int,
    ) {
        constructor(teamId: Int, k: Int) : this(teamId, 0, IntArray(k + 1), -1)

        val sumByScore: Int
            get() = scores.sum()
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val (n, k, id, m) = readLine()!!.split(" ").map { it.toInt() }
        val arr = Array(m) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

        Problem3758().solution(n, k, id, arr).also { println(it) }
    }
}
