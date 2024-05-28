package com.keunwon.algorithm.baekjoon

class Problem9935 {
    fun solution(text: String, boom: String): String {
        val list = mutableListOf<Char>()

        for (c in text) {
            list.add(c)
            if (list.size < boom.length) continue

            val flag = boom.indices.all { boom[boom.lastIndex - it] == list[list.lastIndex - it] }
            if (flag) {
                repeat(boom.length) { list.removeLast() }
            }
        }
        return list.joinToString("").ifBlank { "FRULA" }
    }
}

fun main() {
    val text = readln()
    val boom = readln()

    println(Problem9935().solution(text, boom))
}
