package com.keunwon.algorithm.baekjoon

/**
 * Title: 회문
 * Level: 골드-5
 **/
class Problem17609 {
    fun solution(word: String): Int {
        val left = 0
        val right = word.lastIndex
        return dfs(left, right, word, false)
    }

    private fun dfs(left: Int, right: Int, word: String, remove: Boolean): Int {
        var l = left
        var r = right

        while (l <= r) {
            if (word[l] == word[r]) {
                l++
                r--
                continue
            }

            if (remove) return 2

            val check1 = dfs(l + 1, r, word, true)
            val check2 = dfs(l, r - 1, word, true)

            return if (check1 == 0 || check2 == 0) 1 else 2
        }
        return 0
    }
}

fun main() {
    val n = readln()!!.toInt()

    repeat(n) {
        val word = readLine()!!
        Problem17609().solution(word).also(::println)
    }
}
