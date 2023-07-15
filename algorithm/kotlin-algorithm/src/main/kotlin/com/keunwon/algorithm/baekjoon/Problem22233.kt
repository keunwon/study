package com.keunwon.algorithm.baekjoon

/**
 * Title: 가희와 키워드
 * Level: 실버-2
 **/
class Problem22233 {
    fun solution(memos: Array<String>, blogs: Array<String>): IntArray {
        val memosSet = memos.toMutableSet()
        return blogs.map { str ->
            val tmp = str.split(",").toSet()
            memosSet.removeAll(tmp)
            memosSet.size
        }.toIntArray()
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val memos = Array(n) { readLine()!! }
    val blogs = Array(m) { readLine()!! }

    Problem22233().solution(memos, blogs).also { println(it.joinToString(System.lineSeparator())) }
}
