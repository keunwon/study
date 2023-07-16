package com.keunwon.algorithm.baekjoon

/**
 * Title: A와 B 2
 * Level: 골드-5
 **/
class Problem12919 {
    fun solution(s: String, t: String): Int {
        return if (dfs(s, t)) 1 else 0
    }

    private fun dfs(s: String, t: String): Boolean {
        if (s.length == t.length) return s == t

        if (t.last() == 'A') {
            val tmp = t.take(t.lastIndex)
            if (dfs(s, tmp)) return true
        }

        if (t.first() == 'B') {
            val tmp = t.substring(1).reversed()
            if (dfs(s, tmp)) return true
        }
        return false
    }
}

fun main() {
    val s = readLine()!!
    val t = readLine()!!

    Problem12919().solution(s, t).also { println(it) }
}
