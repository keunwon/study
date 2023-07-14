package com.keunwon.algorithm.baekjoon

/**
 * Title: 영단어 암기는 괴로워
 * Level: 실버-3
 **/
class Problem20920

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = hashMapOf<String, Int>()

    for (i in 0 until n) {
        val word = readLine()
        if (word.length >= m) map[word] = map.getOrDefault(word, 0) + 1
    }

    val order = map.entries.sortedWith(compareBy({ -it.value }, { -it.key.length }, { it.key }))
    order.forEach { bw.write("${it.key}\n") }

    bw.flush()
    bw.close()
}
