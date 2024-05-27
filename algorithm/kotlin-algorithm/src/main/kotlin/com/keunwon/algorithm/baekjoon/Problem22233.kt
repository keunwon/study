package com.keunwon.algorithm.baekjoon

class Problem22233 {
    fun solution(memo: Array<String>, blog: Array<String>): IntArray {
        val set = memo.toMutableSet()
        return blog.map { s ->
            val list = s.split(",").toSet()
            set.removeAll(list)
            set.size
        }.toIntArray()
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val memo = Array(n) { readln() }
    val blog = Array(m) { readln() }

    Problem22233().solution(memo, blog).also { println(it.joinToString(System.lineSeparator())) }
}
