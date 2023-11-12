package com.keunwon.algorithm.againresolve

class ALessons68936 {
    private lateinit var arr: Array<IntArray>
    private val answer = intArrayOf(0, 0)

    fun solution(arr: Array<IntArray>): IntArray {
        this.arr = arr
        dfs(0, 0, arr.size)
        return answer
    }

    private fun dfs(r: Int, c: Int, size: Int) {
        if (check(r, c, size)) {
            if (arr[r][c] == 0) ++answer[0] else ++answer[1]
            return
        }

        val newSize = size / 2
        dfs(r, c, newSize)
        dfs(r, c + newSize, newSize)
        dfs(r + newSize, c, newSize)
        dfs(r + newSize, c + newSize, newSize)
    }

    private fun check(r: Int, c: Int, size: Int): Boolean {
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
    ALessons68936().solution(
        arrayOf(
            intArrayOf(1, 1, 0, 0),
            intArrayOf(1, 0, 0, 0),
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1)
        )
    ).let { println(it.contentToString()) }

    ALessons68936().solution(
        arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
            intArrayOf(0, 1, 0, 0, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1)
        )
    ).let { println(it.contentToString()) }
}
