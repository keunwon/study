package com.keunwon.algorithm.programmers

/**
 * Title: 단어 변환
 * Level: 3
 **/
class Lessons43163 {
    private var answer = Int.MAX_VALUE

    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (words.none { it != target }) return 0
        dfs(0, begin, target, BooleanArray(words.size), words)
        return if (answer == Int.MAX_VALUE) 0 else answer
    }

    private fun dfs(depth: Int, begin: String, target: String, visited: BooleanArray, words: Array<String>) {
        if (begin == target) {
            answer = answer.coerceAtMost(depth)
            return
        }

        for ((i, word) in words.withIndex()) {
            if (!visited[i] && check(begin, word)) {
                visited[i] = true
                dfs(depth + 1, word, target, visited, words)
                visited[i] = false
            }
        }
    }

    private fun check(str: String, target: String): Boolean {
        return str.filterIndexed { index, c -> c != target[index] }.count() == 1
    }
}
