package algorithm.programmers

import kotlin.math.min

class Lesson43163 {
    private var answer = Int.MAX_VALUE

    fun solution(begin: String, target: String, words: Array<String>): Int {
        dfs(0, begin, target, BooleanArray(words.size), words)
        return if (answer == Int.MAX_VALUE) 0 else answer
    }

    private fun dfs(depth: Int, begin: String, target: String, visited: BooleanArray, words: Array<String>) {
        if (depth == words.size) return

        if (begin == target) {
            answer = min(answer, depth)
            return
        }

        for ((i, word) in words.withIndex()) {
            val diff = begin.indices.count { begin[it] != word[it] }
            if (diff <= 1 && !visited[i]) {
                visited[i] = true
                dfs(depth + 1, word, target, visited, words)
                visited[i] = false
            }
        }
    }
}
