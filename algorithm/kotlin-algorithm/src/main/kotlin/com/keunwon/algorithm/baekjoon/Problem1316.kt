package com.keunwon.algorithm.baekjoon

/**
 * Title: 그룹 단어 체커
 * Level: 실버-5
 **/
class Problem1316 {
    fun solution(words: Array<String>): Int {
        return words.count { word ->
            val countMap = ('a'..'z').associateWith { 0 }.toMutableMap()
            var prev = ' '

            for (c in word) {
                if (prev != c) {
                    if (countMap.getValue(c) > 0) return@count false
                    else {
                        countMap[c] = countMap[c]!! + 1
                        prev = c
                    }
                }
            }
            true
        }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val words = Array(n) { readLine()!! }

    Problem1316().solution(words).also(::println)
}
