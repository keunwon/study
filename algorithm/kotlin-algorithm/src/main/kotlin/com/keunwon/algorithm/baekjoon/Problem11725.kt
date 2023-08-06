package com.keunwon.algorithm.baekjoon

/**
 * Title: 트리의 부모 찾기
 * Level: 실버-2
 **/
class Problem11725 {
    private lateinit var parent: IntArray
    private lateinit var graph: Array<MutableList<Int>>

    fun solution(n: Int, arr: Array<Pair<Int, Int>>): IntArray {
        this.parent = IntArray(n + 1)
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            arr.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }

        dfs(1)
        return parent.sliceArray(2..parent.lastIndex)
    }

    private fun dfs(n: Int) {
        for (next in graph[n]) {
            if (parent[next] == 0) {
                parent[next] = n
                dfs(next)
            }
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
