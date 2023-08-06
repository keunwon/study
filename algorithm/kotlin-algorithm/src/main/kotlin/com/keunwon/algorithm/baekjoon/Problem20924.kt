package com.keunwon.algorithm.baekjoon

/**
 * Title: 트리의 기둥과 기지
 * Level: 골드-4
 **/
class Problem20924 {
    private lateinit var graph: Array<MutableList<Node>>
    private lateinit var visited: BooleanArray
    private var gigaNode = 0

    fun solution(n: Int, r: Int, arr: Array<Triple<Int, Int, Int>>): Pair<Int, Int> {
        this.graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            arr.forEach { (a, b, c) ->
                this[a].add(Node(b, c))
                this[b].add(Node(a, c))
            }
        }
        this.visited = BooleanArray(n + 1)

        return if (graph[r].size == 1) {
            gigaNode = r
            pillarDistance(r) to combination(gigaNode, -1, 0)
        } else {
            0 to combination(r, -1, 0)
        }
    }

    private fun combination(index: Int, prev: Int, sum: Int): Int {
        if (graph[index].size == 1) return sum

        var count = 0
        for (next in graph[index]) {
            if (visited[next.index]) continue
            if (prev == next.index) continue

            visited[next.index] = true
            count = count.coerceAtLeast(combination(next.index, index, sum + next.distance))
            visited[next.index] = false
        }
        return count
    }

    private fun pillarDistance(index: Int): Int {
        if (graph[index].size >= 3) {
            gigaNode = index
            return 0
        }

        var answer = 0
        for (next in graph[index]) {
            if (!visited[next.index]) {
                visited[index] = true
                answer += pillarDistance(next.index) + next.distance
            }
        }
        return answer
    }

    private data class Node(val index: Int, val distance: Int)
}

fun main() {
    val (n, r) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(n - 1) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem20924().solution(n, r, arr).also { (f, s) -> println("$f $s") }
}
