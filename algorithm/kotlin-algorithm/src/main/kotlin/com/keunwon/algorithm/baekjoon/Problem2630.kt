package com.keunwon.algorithm.baekjoon

/**
 * Title: 색종이 만들기
 * Level: 실버-2
 **/
class Problem2630 {
    private lateinit var map: Array<IntArray>
    private val answer = IntArray(2)

    fun solution(map: Array<IntArray>): IntArray {
        this.map = map
        dfs(0, 0, map.size)
        return answer
    }

    private fun dfs(y: Int, x: Int, size: Int) {
        if (size == 1 || check(y, x, size)) {
            if (map[y][x] == 0) answer[0]++ else answer[1]++
            return
        }

        val mid = size / 2
        dfs(y, x, mid)
        dfs(y, x + mid, mid)
        dfs(y + mid, x, mid)
        dfs(y + mid, x + mid, mid)
    }

    private fun check(y: Int, x: Int, size: Int): Boolean {
        val target = map[y][x]

        for (i in y until y + size) {
            for (j in x until x + size) {
                if (map[i][j] != target) return false
            }
        }
        return true
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val n = readLine().toInt()
    val map = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    Problem2630().solution(map).forEach(::println)
}
