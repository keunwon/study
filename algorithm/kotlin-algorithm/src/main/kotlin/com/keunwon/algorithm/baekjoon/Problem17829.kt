package com.keunwon.algorithm.baekjoon

/**
 * Title: 222-풀링
 * Level: 실버-2
 **/
class Problem17829 {
    private lateinit var map: Array<IntArray>

    fun solution(map: Array<IntArray>): Int {
        this.map = map
        return dfs(0, 0, map.size)
    }

    private fun dfs(r: Int, c: Int, size: Int): Int {
        if (size == 2) {
            val arr = IntArray(4)
            var index = 0

            for (i in r until r + size) {
                for (j in c until c + size) {
                    arr[index++] = map[i][j]
                }
            }
            arr.sort()
            return arr[2]
        }

        val mid = size / 2
        val arr = intArrayOf(
            dfs(r, c, mid),
            dfs(r, c + mid, mid),
            dfs(r + mid, c, mid),
            dfs(r + mid, c + mid, mid),
        ).apply { sort() }
        return arr[2]
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val n = readLine().toInt()
    val map = Array(n) {
        readLine().split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem17829().solution(map).also { bw.write("$it") }

    bw.flush()
    bw.close()
    close()
}
