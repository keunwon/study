package com.keunwon.algorithm.againresolve

class AProblem14712 {
    private lateinit var map: Array<BooleanArray>

    private var n = 0
    private var m = 0
    private var answer = 0

    fun solution(n: Int, m: Int): Int {
        this.n = n
        this.m = m
        this.map = Array(n + 1) { BooleanArray(m + 1) }

        dfs(0)
        return answer
    }

    private fun dfs(count: Int) {
        if (n * m == count) {
            answer++
            return
        }

        val r = count / m + 1
        val c = count % m + 1

        if (check(r, c)) {
            dfs(count + 1)
            return
        }

        dfs(count + 1)
        map[r][c] = true
        dfs(count + 1)
        map[r][c] = false
    }

    private fun check(r: Int, c: Int): Boolean {
        return map[r][c - 1] && map[r - 1][c] && map[r - 1][c - 1]
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    AProblem14712().solution(n, m).also(::println)
}
