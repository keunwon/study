package com.keunwon.algorithm.againresolve

class ALessons84512 {
    private val alphabets = arrayOf('A', 'E', 'I', 'O', 'U')
    private val answer = mutableListOf<String>()

    fun solution(word: String): Int {
        dfs(0, "", word)
        return answer.indexOf(word)
    }

    private fun dfs(depth: Int, cur: String, word: String) {
        answer.add(cur)
        if (depth == 5) return

        for (c in alphabets) {
            dfs(depth + 1, "$cur$c", word)
        }
    }
}
