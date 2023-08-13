package com.keunwon.algorithm.againresolve

class AProblem1182 {
    private lateinit var arr: IntArray

    private var s = 0
    private var answer = 0

    fun solution(s: Int, arr: IntArray): Int {
        this.s = s
        this.arr = arr

        dfs(0, 0)
        return if (s == 0) --answer else answer
    }

    private fun dfs(index: Int, sum: Int) {
        if (index == arr.size) {
            if (s == sum) answer++
            return
        }

        dfs(index + 1, sum + arr[index])
        dfs(index + 1, sum)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, s) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    AProblem1182().solution(s, arr).also { bw.write("$it") }

    bw.flush()
    bw.close()
    close()
}
