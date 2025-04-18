package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 성냥개비
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/3687">성냥개비 (골드-2)</a>
 **/
class Problem3687 {
    private val costs = intArrayOf(6, 2, 5, 5, 4, 5, 6, 3, 7, 6)

    fun solution(): List<Pair<String, String>> {
        return listOf()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()
    val pairs = Problem3687().solution()

    repeat(t) {
        val n = br.readLine().toInt()
        bw.write("${pairs[n].first} ${pairs[n].second}")
    }

    bw.flush()
    bw.close()
    br.close()
}
