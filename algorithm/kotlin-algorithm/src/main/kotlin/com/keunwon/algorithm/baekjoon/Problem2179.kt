package com.keunwon.algorithm.baekjoon

/**
 * Title: 비슷한 단어
 * Level: 골드-4
 **/
class Problem2179 {
    fun solution(words: List<String>): Array<String> {
        var max = 0
        var index1 = 0
        var index2 = 0

        for (i in 0 until words.lastIndex) {
            val word1 = words[i]

            for (j in i + 1 until words.size) {
                val word2 = words[j]
                val size = word1.length.coerceAtMost(word2.length)
                var count = 0

                for (k in 0 until size) {
                    if (word1[k] == word2[k]) count++
                    else break
                }

                if (max < count) {
                    max = count
                    index1 = i
                    index2 = j
                }
            }
        }
        return arrayOf(words[index1], words[index2])
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val words = mutableListOf<String>()

    repeat(n) {
        val word = readLine()!!
        if (!words.contains(word)) words.add(word)
    }

    Problem2179().solution(words).forEach { println(it) }
}
