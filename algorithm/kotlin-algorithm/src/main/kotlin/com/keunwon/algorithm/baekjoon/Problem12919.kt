package com.keunwon.algorithm.baekjoon

class Problem12919 {
    fun solution(s: String, t: String): Int = if (dfs(s, t)) 1 else 0

    private fun dfs(s: String, t: String): Boolean {
        if (s.length == t.length) return s == t

        if (t.last() == 'A') {
            val str = t.dropLast(1)
            if (dfs(s, str)) return true
        }

        if (t.first() == 'B') {
            val str = t.drop(1).reversed()
            if (dfs(s, str)) return true
        }
        return false
    }
}

fun main() {
    val s = readln()
    val t = readln()

    println(Problem12919().solution(s, t))
}
