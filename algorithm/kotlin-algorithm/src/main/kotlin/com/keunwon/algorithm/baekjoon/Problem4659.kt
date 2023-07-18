package com.keunwon.algorithm.baekjoon

/**
 * Title: 비밀번호 발음하기
 * Level: 실버-5
 **/
class Problem4659 {
    fun solution(word: String): Boolean {
        if (!hasMoeum(word)) return false

        for (i in 2 until word.length) {
            val tmpArr = charArrayOf(word[i - 2], word[i - 1], word[i])

            if (tmpArr.all { isMoeum(it) }) return false
            if (tmpArr.all { !isMoeum(it) }) return false
        }

        for (i in 1 until word.length) {
            if (word[i - 1] == word[i]) {
                val str = "${word[i - 1]}${word[i]}"
                if (str != "ee" && str != "oo") return false
            }
        }
        return true
    }

    private fun hasMoeum(word: String): Boolean {
        return word.any { it in charArrayOf('a', 'e', 'i', 'o', 'u') }
    }

    private fun isMoeum(w: Char): Boolean = w in charArrayOf('a', 'e', 'i', 'o', 'u')
}

fun main() {
    while (true) {
        val word = readLine()!!
        if (word == "end") break

        Problem4659().solution(word).let { check ->
            "<$word> is " + if (check) "acceptable." else "not acceptable."
        }.also { println(it) }
    }
}
