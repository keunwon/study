package com.keunwon.algorithm.baekjoon

/**
 * Title: 트리의 지름
 * Level: 골드-4
 **/
class Problem1967 {
    private lateinit var graph: Array<MutableList<Node>>

    fun solution(n: Int, arr: Array<Triple<Int, Int, Int>>): Int {
        this.graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            arr.forEach { (a, b, c) ->
                this[a].add(Node(b, c))
                this[b].add(Node(a, c))
            }
        }
        return (1..n).maxOf { getDistanceWith(it, BooleanArray(n + 1)) }
    }

    private fun getDistanceWith(index: Int, visited: BooleanArray): Int {
        visited[index] = true

        var answer = 0
        for (next in graph[index]) {
            if (visited[next.index]) continue

            val distance = getDistanceWith(next.index, visited) + next.distance
            answer = answer.coerceAtLeast(distance)
        }
        return answer
    }

    private data class Node(val index: Int, val distance: Int)
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n - 1) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem1967().solution(n, arr).also(::println)
}
