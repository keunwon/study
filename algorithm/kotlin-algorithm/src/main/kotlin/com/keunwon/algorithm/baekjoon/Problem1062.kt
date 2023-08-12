package com.keunwon.algorithm.baekjoon

/**
 * Title: 가르침
 * Level: 골드-4
 **/
class Problem1062 {
    private lateinit var words: List<String>
    private var k = 0
    private var answer = 0

    private val baseAlphabets = charArrayOf('a', 'c', 'i', 'n', 't')
    private val visited = BooleanArray(26).apply {
        baseAlphabets.forEach { this[it - 'a'] = true }
    }

    fun solution(text: Array<String>, k: Int): Int {
        this.words = text.map { it.substring(4, it.length - 4) }
        this.k = k

        dfs(0, 0)
        return answer
    }

    private fun dfs(depth: Int, startIndex: Int) {
        if (depth == k - baseAlphabets.size) {
            var count = 0

            for (word in words) {
                val check = word.all { visited[it - 'a'] }
                if (check) count++
            }
            answer = answer.coerceAtLeast(count)
            return
        }

        for (i in startIndex until 26) {
            if (visited[i]) continue

            visited[i] = true
            dfs(depth + 1, i + 1)
            visited[i] = false
        }
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val text = Array(n) { readLine()!! }

    Problem1062().solution(text, k).also(::println)
}
