package com.keunwon.algorithm.baekjoon

/**
 * Title: 트리
 * Level: 골드-5
 **/
class Problem1068 {
    private lateinit var graph: Array<MutableList<Int>>

    fun solution(arr: IntArray, delete: Int): Int {
        this.graph = Array(arr.size) { mutableListOf() }
        var rootIndex = -1

        arr.forEachIndexed { index, num ->
            if (num == -1) rootIndex = index
            else graph[num].add(index)
        }

        if (rootIndex == delete) return 0

        graph.forEach { list -> list.removeIf { it == delete } }
        return dfs(rootIndex, -1)
    }

    private fun dfs(index: Int, parent: Int): Int {
        if (graph[index].isEmpty()) return 1

        var answer = 0
        for (next in graph[index]) {
            if (parent == next) continue
            answer += dfs(next, index)
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val target = readLine()!!.toInt()

    Problem1068().solution(arr, target).also(::println)
}
