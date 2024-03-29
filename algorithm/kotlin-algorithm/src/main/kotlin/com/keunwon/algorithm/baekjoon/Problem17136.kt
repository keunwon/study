package com.keunwon.algorithm.baekjoon

/**
 * Title: 색종이 붙이기
 * Level: 골드-2
 **/
class Problem17136 {
    private lateinit var map: Array<IntArray>

    private val papers = intArrayOf(0, 5, 5, 5, 5, 5)
    private var answer = Int.MAX_VALUE

    fun solution(map: Array<IntArray>): Int {
        this.map = map

        dfs(0, 0, 0)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(r: Int, c: Int, count: Int) {
        if (count > answer) return

        if (r == 9 && c == 10) {
            answer = answer.coerceAtMost(count)
            return
        }

        if (c == 10) {
            dfs(r + 1, 0, count)
            return
        }

        if (map[r][c] == 0) {
            dfs(r, c + 1, count)
            return
        }

        for (size in 5 downTo 1) {
            if (!check(r, c, size) || papers[size] == 0) continue

            papers[size]--
            attach(r, c, size, 0)

            dfs(r, c + 1, count + 1)

            attach(r, c, size, 1)
            papers[size]++
        }
    }

    private fun attach(r: Int, c: Int, size: Int, type: Int) {
        for (i in r until r + size) {
            for (j in c until c + size) {
                map[i][j] = type
            }
        }
    }

    private fun check(r: Int, c: Int, size: Int): Boolean {
        for (i in r until r + size) {
            for (j in c until c + size) {
                if (i !in map.indices || j !in map[0].indices) return false
                if (map[i][j] == 0) return false
            }
        }
        return true
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val map = Array(10) {
        readLine().split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem17136().solution(map).also { bw.write("$it") }
    bw.flush()
    bw.close()
    close()
}
