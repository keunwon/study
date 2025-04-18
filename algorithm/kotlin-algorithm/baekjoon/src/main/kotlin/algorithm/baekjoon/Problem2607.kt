package algorithm.baekjoon

import kotlin.math.abs

/**
 * <p>
 * 이름: 비슷한 단어
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2607">비슷한 단어 (실버-2)</a>
 **/
class Problem2607 {
    fun solution(words: Array<String>): Int {
        var result = 0
        for (i in 1 until words.size) {
            if (abs(words[0].length - words[i].length) > 1) continue

            val alphabet = IntArray(26).apply { words[0].forEach { ++this[it - 'A'] } }
            var count = 0

            for (c in words[i]) {
                if (alphabet[c - 'A'] > 0) {
                    ++count
                    --alphabet[c - 'A']
                }
            }

            val valid = when (words[0].length) {
                words[i].length -> words[0].length == count || words[0].length - 1 == count
                words[i].length - 1 -> words[0].length == count
                words[i].length + 1 -> words[i].length == count
                else -> false
            }
            if (valid) ++result
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val words = Array(n) { br.readLine() }

    Problem2607().solution(words).also { println(it) }
}
