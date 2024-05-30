package com.keunwon.algorithm.baekjoon

import java.util.StringTokenizer

class Problem1976 {
    private lateinit var parents: IntArray

    fun solution(n: Int, map: Array<IntArray>, plan: IntArray): String {
        this.parents = IntArray(n + 1) { it }

        for (i in map.indices) {
            for ((j, type) in map[i].withIndex()) {
                if (type == 1) union(i, j)
            }
        }

        val target = find(plan[0] - 1)
        return plan.all { target == find(it - 1) }.let {
            if (it) "YES" else "NO"
        }
    }

    private fun find(n: Int): Int = if (parents[n] == n) n else find(parents[n])

    private fun union(a: Int, b: Int) {
        val first = find(a)
        val second = find(b)

        if (first < second) parents[second] = first
        else parents[first] = second
    }
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val map = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }
    val plan = run {
        val st = StringTokenizer(readln())
        IntArray(m) { st.nextToken().toInt() }
    }

    println(Problem1976().solution(n, map, plan))
}
