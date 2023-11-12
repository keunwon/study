package com.keunwon.algorithm.againresolve

class ALessons43163 {
    private lateinit var target: String
    private lateinit var words: Array<String>
    private var answer = Int.MAX_VALUE

    fun solution(begin: String, target: String, words: Array<String>): Int {
        this.target = target
        this.words = words
        dfs(0, begin, BooleanArray(words.size))
        return if (answer == Int.MAX_VALUE) 0 else answer
    }

    private fun dfs(depth: Int, str: String, visited: BooleanArray) {
        if (target == str) {
            answer = answer.coerceAtMost(depth)
            return
        }
        for ((i, word) in words.withIndex()) {
            if (!visited[i] && matches(str, word)) {
                visited[i] = true
                dfs(depth + 1, word, visited)
                visited[i] = false
            }
        }
    }

    private fun matches(s1: String, s2: String): Boolean =
        s1.filterIndexed { i, s -> s != s2[i] }.count() == 1
}

fun main() {
    ALessons43163().solution(
        "hit",
        "cog",
        arrayOf("hot", "dot", "dog", "lot", "log", "cog")
    ).also(::println)

    ALessons43163().solution(
        "hit",
        "cog",
        arrayOf("hot", "dot", "dog", "lot", "log")
    ).also(::println)
}
