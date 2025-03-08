package com.keunwon.algorithm.programmers

import java.util.*
import kotlin.math.abs

class Lesson62050 {
    private lateinit var land: Array<IntArray>
    private lateinit var groupingLand: Array<IntArray>

    private val parents = IntArray(90_001) { it }

    fun solution(land: Array<IntArray>, height: Int): Int {
        this.land = land
        this.groupingLand = Array(land.size) { IntArray(land[0].size) }

        grouping(height)
        return mst(land)
    }

    private fun mst(land: Array<IntArray>): Int {
        val edges = generateEdges(land).sortedBy { it.cost }
        var answer = 0

        for (edge in edges) {
            if (find(edge.aTeam) != find(edge.bTeam)) {
                union(edge.aTeam, edge.bTeam)
                answer += edge.cost
            }
        }
        return answer
    }

    private fun find(n: Int): Int = if (parents[n] == n) n else find(parents[n])

    private fun union(a: Int, b: Int) {
        val first = find(a)
        val second = find(b)

        if (first < second) parents[second] = first
        else parents[first] = second
    }

    private fun generateEdges(land: Array<IntArray>): MutableList<Node> {
        val edges = mutableListOf<Node>()
        for (r in land.indices) {
            for (c in land[0].indices) {
                for ((mr, mc) in moves) {
                    val nr = r + mr
                    val nc = c + mc

                    if (nr !in land.indices || nc !in land[0].indices) continue
                    if (groupingLand[r][c] == groupingLand[nr][nc]) continue

                    val diff = abs(land[r][c] - land[nr][nc])
                    edges.add(Node(groupingLand[r][c], groupingLand[nr][nc], diff))
                }
            }
        }
        return edges
    }

    private fun grouping(height: Int) {
        var teamId = 1
        val visited = Array(groupingLand.size) { BooleanArray(groupingLand[0].size) }

        for (i in land.indices) {
            for (j in land[0].indices) {
                if (!visited[i][j]) {
                    bfs(Pair(i, j), teamId, height, visited)
                    ++teamId
                }
            }
        }
    }

    private fun bfs(start: Pair<Int, Int>, team: Int, h: Int, visited: Array<BooleanArray>) {
        val queue = LinkedList<Pair<Int, Int>>().apply { offer(start) }
        visited[start.first][start.second] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            groupingLand[cur.first][cur.second] = team

            for ((mr, mc) in moves) {
                val (nr, nc) = cur.let { (r, c) -> r + mr to c + mc }

                if (nr !in land.indices || nc !in land[0].indices) continue
                if (visited[nr][nc]) continue

                val diff = abs(land[cur.first][cur.second] - land[nr][nc])
                if (diff <= h) {
                    visited[nr][nc] = true
                    queue.offer(Pair(nr, nc))
                }
            }
        }
    }

    private data class Node(val aTeam: Int, val bTeam: Int, val cost: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}
