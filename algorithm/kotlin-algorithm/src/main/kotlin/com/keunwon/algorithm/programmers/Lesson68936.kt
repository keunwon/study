package com.keunwon.algorithm.programmers

class Lesson68936 {
    private lateinit var map: Array<IntArray>

    val answer = intArrayOf(0, 0)

    fun solution(map: Array<IntArray>): IntArray {
        this.map = map

        dfs(0, 0, map.size)
        return answer
    }

    private fun dfs(r: Int, c: Int, size: Int) {
        if (check(r, c, size)) {
            if (map[r][c] == 0) ++answer[0] else ++answer[1]
            return
        }

        val mid = size / 2
        dfs(r, c, mid)
        dfs(r, c + mid, mid)
        dfs(r + mid, c, mid)
        dfs(r + mid, c + mid, mid)
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
