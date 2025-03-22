package com.keunwon.algorithm.baekjoon

import java.util.PriorityQueue

/**
 * <p>
 * 이름: 택배 배송
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="">택배 배송 (골드-5)</a>
 **/
class Problem5972 {
    fun solution(n: Int, edges: Array<Triple<Int, Int, Int>>): Int {
        val graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            edges.forEach { (u, v, d) ->
                this[u].add(Node(v, d))
                this[v].add(Node(u, d))
            }
        }
        val q = PriorityQueue<Node>(compareBy { it.d }).apply { offer(Node(1, 0)) }
        val dist = IntArray(n + 1) { 1e9.toInt() }.apply { this[1] = 0 }

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if (cur.index == n || cur.d > dist[cur.index]) continue

            for (next in graph[cur.index]) {
                val d = cur.d + next.d
                if (dist[next.index] > d) {
                    dist[next.index] = d
                    q.offer(Node(next.index, d))
                }
            }
        }
        return dist[n]
    }

    private class Node(val index: Int, val d: Int)
}

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val edges = Array(m) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        Triple(arr[0], arr[1], arr[2])
    }
    Problem5972().solution(n, edges).also { println(it) }
}
