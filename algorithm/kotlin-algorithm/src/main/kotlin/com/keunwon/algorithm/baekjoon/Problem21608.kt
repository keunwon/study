package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 상어 초등학교
 * Level: 골드-5
 **/
// todo
class Problem21608 {
    fun solution(arr: IntArray): Pair<Int, Int> {
        return arr.minOf { it } to arr.maxOf { it }
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val t = br.readLine().toInt()

        repeat(t) {
            val n = br.readLine().toInt()
            val st = StringTokenizer(br.readLine())
            val arr = IntArray(n) { st.nextToken().toInt() }

            Problem21608().solution(arr).also {
                bw.write("${it.first} ")
                bw.write("${it.second}")
                bw.newLine()
            }
        }
        bw.flush()
    }
}
