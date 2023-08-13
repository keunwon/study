package com.keunwon.algorithm.baekjoon

/**
 * Title: 좋은 수열
 * Level: 골드-4
 **/
class Problem2661 {
    private var n = 0
    private var finish = false
    private var answer = ""

    fun solution(n: Int): String {
        this.n = n
        dfs("")
        return answer
    }

    private fun dfs(cur: String) {
        if (finish) return

        if (n == cur.length) {
            answer = cur
            finish = true
            return
        }

        for (i in 1..3) {
            val tmp = "$cur$i"
            if (check(tmp)) dfs(tmp)
        }
    }

    private fun check(str: String): Boolean {
        val size = str.length / 2

        for (i in 1..size) {
            val tmp1 = str.substring(str.length - i - i, str.length - i)
            val tmp2 = str.substring(str.length - i)

            if (tmp1 == tmp2) return false
        }
        return true
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem2661().solution(n).also(::println)
}
