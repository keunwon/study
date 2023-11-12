package com.keunwon.algorithm.againresolve

class ALessons84512 {
    private val answer = mutableListOf<String>()

    fun solution(word: String): Int {
        dfs("", word)
        return answer.indexOf(word)
    }

    private fun dfs(str: String, word: String) {
        answer.add(str)
        if (str.length == 5) return

        for (c in charArrayOf('A', 'E', 'I', 'O', 'U')) {
            dfs("$str$c", word)
        }
    }
}

fun main() {
    arrayOf("AAAAE", "AAAE", "I", "EIO").forEach {
        println(ALessons84512().solution(it))
    }
}
