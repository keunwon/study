package com.keunwon.algorithm.againresolve

class AProblem1062 {
    private lateinit var words: List<String>

    private var k = 0
    private val alphabets = BooleanArray(26)
    private var answer = 0

    fun solution(k: Int, words: Array<String>): Int {
        this.k = k
        this.words = words.map { it.substring(4, it.length - 4) }

        charArrayOf('a', 'c', 'i', 'n', 't').forEach {
            alphabets[it - 'a'] = true
        }
        dfs(0, 0)
        return answer
    }

    private fun dfs(depth: Int, startIndex: Int) {
        if (k - 5 == depth) {
            val matchCount = words.count { word -> word.all { alphabets[it - 'a'] } }
            answer = answer.coerceAtLeast(matchCount)
            return
        }

        for (i in startIndex until alphabets.size) {
            if (alphabets[i]) continue

            alphabets[i] = true
            dfs(depth + 1, i + 1)
            alphabets[i] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, k) = readLine().split(" ").map { it.toInt() }
    val words = Array(n) { readLine() }

    AProblem1062().solution(k, words).also { bw.write("$it") }

    bw.flush()
    bw.close()
    close()
}
