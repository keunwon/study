package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 여행 가자
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1976">여행 가자 (골드-4)</a>
 **/
class Problem1976 {
    fun solution(edges: Array<IntArray>, route: IntArray): String {
        val parents = IntArray(edges.size + 1) { it }

        for (i in edges.indices) {
            for ((j, type) in edges[i].withIndex()) {
                if (type == 1) union(i + 1, j + 1, parents)
            }
        }

        val target = find(route[0], parents)
        return if (route.all { target == find(it, parents) }) "YES" else "NO"
    }

    private fun union(a: Int, b: Int, parent: IntArray) {
        val find1 = find(a, parent)
        val find2 = find(b, parent)
        if (find1 < find2) parent[find2] = find1 else parent[find1] = find2
    }

    private fun find(n: Int, parent: IntArray): Int =
        if (parent[n] == n) n else find(parent[n], parent)
}

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val edges = Array(n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }
    val route = run {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(m) { arr[it] }
    }

    Problem1976().solution(edges, route).also { println(it) }
}
