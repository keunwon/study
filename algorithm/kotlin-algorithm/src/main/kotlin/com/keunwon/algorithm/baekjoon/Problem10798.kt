package com.keunwon.algorithm.baekjoon

/**
 * Title: 세로 읽기
 * Level: 브론즈-1
 **/
class Problem10798 {
    fun solution(arr: List<String>): String {
        val max = arr.maxOf { it.length }
        val words = arr.map { it + " ".repeat(max - it.length) }
        val answer = StringBuilder()

        for (i in 0 until max) {
            for (j in arr.indices) {
                if (words[j][i] != ' ') answer.append(words[j][i])
            }
        }
        return answer.toString()
    }
}

fun main() {
    val arr = List(5) { readLine()!! }
    Problem10798().solution(arr).also(::println)
}
