package com.keunwon.algorithm.againresolve

import java.util.*

/**
 * Title: 트리의 지름
 * Level: 골드-4
 **/
class AProblem1967 {
    private lateinit var graph: Array<MutableList<Node>>

    fun solution(n: Int, edges: Array<Triple<Int, Int, Int>>): Int {
        this.graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            edges.forEach { (a, b, c) ->
                this[a].add(Node(b, c))
                this[b].add(Node(a, c))
            }
        }
        return (1..n).maxOf { getDistance(it, BooleanArray(n + 1)) }
    }

    private fun getDistance(index: Int, visited: BooleanArray): Int {
        visited[index] = true

        var answer = 0
        for (next in graph[index]) {
            if (visited[next.index]) continue

            val distance = getDistance(next.index, visited) + next.distance
            answer = answer.coerceAtLeast(distance)
        }
        return answer
    }

    private data class Node(val index: Int, val distance: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val edges = Array(n - 1) {
            val st = StringTokenizer(br.readLine())
            Triple(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
        }

        AProblem1967().solution(n, edges).also { bw.write("$it") }
        bw.flush()
    }
}
