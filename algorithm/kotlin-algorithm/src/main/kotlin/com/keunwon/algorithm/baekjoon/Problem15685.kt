package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 드래곤 커브
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/15685">드래곤 커브 (골드-3)</a>
 **/
class Problem15685 {
    fun solution(infos: Array<IntArray>): Int {
        // 0:(→) 1:(↑) 2:(←) 3:(↓)
        val moves = arrayOf(0 to 1, -1 to 0, 0 to -1, 1 to 0)
        val map = Array(101) { BooleanArray(101) }

        for ((x, y, d, g) in infos) {
            val dirs = mutableListOf(d)

            repeat(g) {
                val lastIndex = dirs.lastIndex
                for (i in 0..lastIndex) {
                    val nd = (dirs[lastIndex - i] + 1) % 4
                    dirs.add(nd)
                }
            }

            map[y][x] = true
            var nx = x
            var ny = y

            for (dir in dirs) {
                val (my, mx) = moves[dir]
                nx += mx
                ny += my
                map[ny][nx] = true
            }
        }

        var result = 0
        for (i in 0 until map.lastIndex) {
            for (j in 0 until map[0].lastIndex) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    ++result
                }
            }
        }
        return result
    }
}

fun main() {
    val n = readln().toInt()
    val infos = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    Problem15685().solution(infos).also(::println)
}
