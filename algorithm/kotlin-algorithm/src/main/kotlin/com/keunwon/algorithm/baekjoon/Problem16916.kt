package com.keunwon.algorithm.baekjoon

/**
 * Title: 부분 문자열
 * Level: 브론즈-2
 **/
class Problem16916 {
    fun solution(p: String, s: String): Int {
        return if (kmp(p, s)) 1 else 0
    }

    private fun kmp(word: String, findWord: String): Boolean {
        val table = createTable(findWord)
        var index = 0
        var result = false

        for (i in word.indices) {
            while (index > 0 && word[i] != findWord[index]) {
                index = table[index - 1]
            }

            if (word[i] == findWord[index]) {
                if (index == findWord.lastIndex) {
                    result = true
                    index = table[index]
                } else index++
            }
        }
        return result
    }

    private fun createTable(suffix: String): IntArray {
        val table = IntArray(suffix.length)
        var index = 0

        for (i in 1 until table.size) {
            while (index > 0 && suffix[i] != suffix[index]) {
                index = table[index - 1]
            }

            if (suffix[i] == suffix[index]) {
                index++
                table[i] = index
            }
        }
        return table
    }
}

fun main() {
    val p = readLine()!!
    val s = readLine()!!

    Problem16916().solution(p, s).also(::println)
}
