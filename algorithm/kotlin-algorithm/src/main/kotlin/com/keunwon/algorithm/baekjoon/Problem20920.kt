package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 영단어 암기는 괴로워
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20920">영단어 암기는 괴로워 (실버-3)</a>
 **/
class Problem20920 {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = HashMap<String, Int>(n)

        for (i in 0 until n) {
            val word = br.readLine()
            if (word.length >= m) map[word] = map.getOrDefault(word, 0) + 1
        }

        map.entries.sortedWith(compareBy({ -it.value }, { -it.key.length }, { it.key })).forEach {
            bw.write(it.key)
            bw.newLine()
        }

        bw.flush()
        bw.close()
        br.close()
    }
}

fun main() {
    Problem20920().solution()
}
