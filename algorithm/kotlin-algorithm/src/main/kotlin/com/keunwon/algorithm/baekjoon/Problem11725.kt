package com.keunwon.algorithm.baekjoon

/**
 * Title: 트리의 부모 찾기
 * Level: 실버-2
 **/
class Problem11725 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var parent: IntArray

    fun solution(n: Int, arr: Array<Pair<Int, Int>>): IntArray {
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            arr.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }
        this.parent = IntArray(n + 1)

        dfs(1)
        return parent.sliceArray(2..n)
    }

    private fun dfs(index: Int) {
        for (next in graph[index]) {
            if (parent[next] != 0) continue

            parent[next] = index
            dfs(next)
        }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n - 1) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    Problem11725().solution(n, arr).forEach(::println)
}
