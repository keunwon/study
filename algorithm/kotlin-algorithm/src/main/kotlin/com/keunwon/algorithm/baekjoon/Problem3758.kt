package com.keunwon.algorithm.baekjoon

import kotlin.math.max

class Problem3758 {
    fun solution(n: Int, k: Int, targetId: Int, logs: Array<IntArray>): Int {
        val teams = Array(n) { Team(it + 1, 0, IntArray(k + 1), -1) }

        logs.forEachIndexed { index, (id, no, point) ->
            teams[id - 1].apply {
                request += 1
                points[no] = max(points[no], point)
                last = index
            }
        }
        return teams.sortedWith(compareBy({ -it.sum() }, { it.request }, { it.last }))
            .indexOfFirst { it.teamId == targetId } + 1
    }

    private class Team(
        val teamId: Int,
        var request: Int,
        val points: IntArray,
        var last: Int,
    ) {
        fun sum(): Int = points.sum()
    }
}

fun main() {
    repeat(readln().toInt()) {
        val (n, k, targetId, m) = readln().split(" ").map { it.toInt() }
        val logs = Array(m) { readln().split(" ").map { it.toInt() }.toIntArray() }

        println(Problem3758().solution(n, k, targetId, logs))
    }
}
