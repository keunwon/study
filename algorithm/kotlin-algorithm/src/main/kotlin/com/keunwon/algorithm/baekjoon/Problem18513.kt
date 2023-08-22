package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 샘터
 * Level: 골드-4
 **/
class Problem18513 {
    fun solution(n: Int, k: Int, arr: IntArray): Int {
        return 0
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(br.readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
       
        Problem18513().solution(n, k, arr).also { bw.write("$it") }
        bw.flush()
    }
}

