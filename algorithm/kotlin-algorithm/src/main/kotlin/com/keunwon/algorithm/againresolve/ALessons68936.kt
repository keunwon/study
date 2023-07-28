package com.keunwon.algorithm.againresolve

class ALessons68936 {
    private val answer = IntArray(2)

    fun solution(arr: Array<IntArray>): IntArray {
        dfs(0, 0, arr.size, arr)
        return answer
    }

    private fun dfs(r: Int, c: Int, size: Int, arr: Array<IntArray>) {
        if (check(r, c, size, arr)) {
            if (arr[r][c] == 0) answer[0]++ else answer[1]++
            return
        }
        val n = size / 2
        dfs(r, c, n, arr)
        dfs(r, c + n, n, arr)
        dfs(r + n, c, n, arr)
        dfs(r + n, c + n, n, arr)
    }

    private fun check(r: Int, c: Int, size: Int, arr: Array<IntArray>): Boolean {
        val target = arr[r][c]
        for (i in r until r + size) {
            for (j in c until c + size) {
                if (arr[i][j] != target) return false
            }
        }
        return true
    }
}

fun main() {
    val arr = arrayOf(
        intArrayOf(1, 1, 0, 0),
        intArrayOf(1, 0, 0, 0),
        intArrayOf(1, 0, 0, 1),
        intArrayOf(1, 1, 1, 1),
    )
    ALessons68936().solution(arr).also { println(it.joinToString(", ")) }
}
