package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 마법사 상어와 파이어볼
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20056">마법사 상어와 파이어볼 (골드-4)</a>
 **/
class Problem20056 {
    fun solution(n: Int, k: Int, infos: Array<IntArray>): Int {
        val moves = arrayOf(-1 to 0, -1 to 1, 0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1)
        val fireBalls = infos.map { FireBall(it[0], it[1], it[2], it[3], it[4]) }.toMutableList()

        repeat(k) {
            val board = Array(n) { Array(n) { mutableListOf<FireBall>() } }.apply {
                fireBalls.onEach { fb ->
                    fb.r = (fb.r + (fb.s % n * moves[fb.d].first) + n) % n
                    fb.c = (fb.c + (fb.s % n * moves[fb.d].second) + n) % n
                    this[fb.r][fb.c].add(fb)
                }
            }

            for (i in board.indices) {
                for ((j, list) in board[i].withIndex()) {
                    if (list.size < 2) continue

                    val m = list.sumOf { it.m } / 5
                    val s = list.sumOf { it.s } / list.size
                    val dirs = if (list.all { it.d % 2 == 0 } || list.all { it.d % 2 == 1 }) {
                        intArrayOf(0, 2, 4, 6)
                    } else {
                        intArrayOf(1, 3, 5, 7)
                    }

                    list.forEach(fireBalls::remove)
                    if (m > 0) {
                        dirs.forEach { fireBalls.add(FireBall(i, j, m, s, it)) }
                    }
                }
            }
        }

        return fireBalls.sumOf { it.m }
    }

    private class FireBall(var r: Int, var c: Int, val m: Int, val s: Int, val d: Int)
}

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val infos = Array(m) {
        val arr = readln().split(" ")
        IntArray(5) { arr[it].toInt() }
    }

    Problem20056().solution(n, k, infos).also(::println)
}
