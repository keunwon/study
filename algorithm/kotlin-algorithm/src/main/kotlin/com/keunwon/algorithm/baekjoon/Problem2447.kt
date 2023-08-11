package com.keunwon.algorithm.baekjoon

/**
 * Title: 별 찍기 - 10
 * Level: 골드-5
 **/
class Problem2447 {
    private lateinit var answer: Array<Array<String>>

    fun solution(n: Int): Array<Array<String>> {
        this.answer = Array(n) { Array(n) { " " } }
        dfs(0, 0, n)
        return answer
    }

    private fun dfs(r: Int, c: Int, size: Int) {
        if (size == 1) {
            answer[r][c] = "*"
            return
        }

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (i == 1 && j == 1) continue
                dfs(r + i * (size / 3), c + j * (size / 3), size / 3)
            }
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val n = readLine().toInt()

    Problem2447().solution(n).also { arr ->
        for (i in arr.indices) {
            bw.write(arr[i].joinToString(""))
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    close()
}
