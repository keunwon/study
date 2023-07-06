package com.keunwon.algorithm.programmers

/**
 * Title: 모음사전
 * Level: 2
 **/
class Lessons84512 {
    private val list = mutableListOf<String>()

    fun solution(word: String): Int {
        dfs("", word)
        return list.indexOf(word)
    }

    private fun dfs(str: String, word: String) {
        list.add(str)
        if (str.length == 5) return

        for (alphabet in arrayOf("A", "E", "I", "O", "U")) {
            dfs(str + alphabet, word)
        }
    }
}
