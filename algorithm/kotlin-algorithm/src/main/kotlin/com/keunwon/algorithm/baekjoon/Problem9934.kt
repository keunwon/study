package com.keunwon.algorithm.baekjoon

/**
 * Title: 완전 이진 트리
 * Level: 실버-1
 **/
class Problem9934 {
    private lateinit var answer: Array<MutableList<Int>>

    fun solution(k: Int, build: IntArray): Array<String> {
        this.answer = Array(k) { mutableListOf<Int>() }
        dfs(0, build)
        return answer.map { it.joinToString(" ") }.toTypedArray()
    }

    private fun dfs(depth: Int, build: IntArray) {
        val mid = build.size / 2
        answer[depth].add(build[mid])

        if (build.size == 1) return

        dfs(depth + 1, build.sliceArray(0 until mid))
        dfs(depth + 1, build.sliceArray(mid + 1 until build.size))
    }
}

fun main() {
    val k = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem9934().solution(k, arr).forEach(::println)
}
