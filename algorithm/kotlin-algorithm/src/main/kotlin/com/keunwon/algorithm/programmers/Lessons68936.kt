package com.keunwon.algorithm.programmers

/**
 * Title: 쿼드압축 후 개수 세기
 * Level: 2
 **/
class Lessons68936 {
    private val answer = IntArray(2)

    fun solution(arr: Array<IntArray>): IntArray {
        dfs(0, 0, arr.size, arr)
        return answer
    }

    private fun dfs(x: Int, y: Int, size: Int, arr: Array<IntArray>) {
        if (check(x, y, size, arr)) {
            if (arr[y][x] == 0) answer[0]++ else answer[1]++
            return
        }

        val n = size / 2
        dfs(x, y, n, arr)
        dfs(x + n, y, n, arr)
        dfs(x, y + n, n, arr)
        dfs(x + n, y + n, n, arr)
    }

    private fun check(x: Int, y: Int, size: Int, arr: Array<IntArray>): Boolean {
        val num = arr[y][x]
        for (i in y until y + size) {
            for (j in x until x + size) {
                if (arr[i][j] != num) return false
            }
        }
        return true
    }
}
