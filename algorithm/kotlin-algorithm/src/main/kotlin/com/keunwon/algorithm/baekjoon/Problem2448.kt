package com.keunwon.algorithm.baekjoon

/**
 * Title: 별 찍기 - 11
 * Level: 골드-4
 **/
class Problem2448 {
    private lateinit var answer: Array<Array<Char>>

    fun solution(n: Int): Array<Array<Char>> {
        this.answer = Array(n) { Array(n * 2 - 1) { ' ' } }

        dfs(0, n - 1, n)
        return answer
    }

    private fun dfs(r: Int, c: Int, size: Int) {
        if (size == 3) {
            answer[r][c] = '*'

            answer[r + 1][c - 1] = '*'
            answer[r + 1][c + 1] = '*'

            answer[r + 2][c - 2] = '*'
            answer[r + 2][c - 1] = '*'
            answer[r + 2][c] = '*'
            answer[r + 2][c + 1] = '*'
            answer[r + 2][c + 2] = '*'
            return
        }

        val n = size / 2
        dfs(r, c, n)
        dfs(r + n, c - n, n)
        dfs(r + n, c + n, n)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val n = readLine().toInt()

    Problem2448().solution(n).forEach {
        bw.write(it.joinToString(""))
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
