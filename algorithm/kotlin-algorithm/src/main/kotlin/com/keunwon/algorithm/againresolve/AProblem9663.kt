package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class AProblem9663 {
    private lateinit var map: IntArray
    private var answer = 0

    fun solution(n: Int): Int {
        this.map = IntArray(n)
        dfs(0)
        return answer
    }

    private fun dfs(depth: Int) {
        if (depth == map.size) {
            answer++
            return
        }

        for (i in map.indices) {
            map[depth] = i
            if (check(depth)) dfs(depth + 1)
        }
    }

    private fun check(depth: Int): Boolean {
        for (i in 0 until depth) {
            if (map[i] == map[depth]) return false

            if (abs(map[depth] - map[i]) == depth - i) return false
        }
        return true
    }
}

fun main() {
    val n = readLine()!!.toInt()
    AProblem9663().solution(n).also { println(it) }
}
