package com.keunwon.algorithm.baekjoon

/**
 * Title: 부분 문자열
 * Level: 브론즈-2
 **/
class Problem16916 {
    fun solution(p: String, s: String): Int {
        return if (kmp(p, s)) 1 else 0
    }

    private fun kmp(original: String, pattern: String): Boolean {
        val table = createTable(pattern)
        var index = 0
        var answer = false

        for (i in original.indices) {
            while (index > 0 && original[i] != pattern[index]) {
                index = table[index - 1]
            }

            if (original[i] == pattern[index]) {
                if (index == pattern.lastIndex) {
                    table[index] = index
                    answer = true
                } else index++
            }
        }
        return answer
    }

    private fun createTable(pattern: String): IntArray {
        val table = IntArray(pattern.length)
        var index = 0

        for (i in 1 until pattern.length) {
            while (index > 0 && pattern[i] != pattern[index]) {
                index = table[index - 1]
            }

            if (pattern[i] == pattern[index]) {
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
