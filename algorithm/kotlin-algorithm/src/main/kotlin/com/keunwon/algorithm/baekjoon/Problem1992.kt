package com.keunwon.algorithm.baekjoon

/**
 * Title: 쿼드트리
 * Level: 실버-1
 **/
class Problem1992 {
    private lateinit var map: Array<IntArray>
    private val answer = StringBuilder()

    fun solution(map: Array<IntArray>): String {
        this.map = map
        dfs(0, 0, map.size)
        return answer.toString()
    }

    private fun dfs(r: Int, c: Int, size: Int) {
        if (size == 1 || check(r, c, size)) {
            answer.append(map[r][c])
            return
        }

        val mid = size / 2

        answer.append("(")
        dfs(r, c, mid)
        dfs(r, c + mid, mid)
        dfs(r + mid, c, mid)
        dfs(r + mid, c + mid, mid)
        answer.append(")")
    }

    private fun check(r: Int, c: Int, size: Int): Boolean {
        val target = map[r][c]

        for (i in r until r + size) {
            for (j in c until c + size) {
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
        readLine().map { it.digitToInt() }.toIntArray()
    }

    Problem1992().solution(map).also { bw.write("$it") }

    bw.flush()
    bw.close()
    close()
}
