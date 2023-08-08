package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 끝나지 않는 파티
 * Level: 골드-5
 **/
class Problem11265 {
    fun solution(parties: Array<IntArray>, edges: Array<Triple<Int, Int, Int>>): Array<String> {
        return emptyArray()
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val roads = Array(n) {
        val st = StringTokenizer(readLine()!!)
        IntArray(n) { st.nextToken().toInt() }
    }
    val edges = Array(m) {
        val st = StringTokenizer(readLine()!!)
        Triple(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }

    Problem11265().solution(roads, edges).forEach(::println)
}
