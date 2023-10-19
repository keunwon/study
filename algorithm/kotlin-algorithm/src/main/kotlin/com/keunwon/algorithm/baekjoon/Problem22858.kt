package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 원상 복구 (small)
 * Level: 실버-3
 **/
class Problem22858 {
    fun solution(k: Int, cards: IntArray, shuffleOrders: IntArray): IntArray {
        repeat(k) { shuffle(cards, shuffleOrders) }
        return cards.sliceArray(1 until cards.size)
    }

    private fun shuffle(cards: IntArray, shuffleOrders: IntArray) {
        val tmp = IntArray(cards.size)

        for (i in 1 until cards.size) tmp[shuffleOrders[i]] = cards[i]

        for (i in 1 until cards.size) cards[i] = tmp[i]
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        val readIntArray = {
            val st = StringTokenizer(br.readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        val cards = intArrayOf(0, *readIntArray())
        val shuffleOrders = intArrayOf(0, *readIntArray())

        Problem22858().solution(k, cards, shuffleOrders).also {
            bw.write(it.joinToString(" "))
        }
        bw.flush()
    }
}
