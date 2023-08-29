package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 텀 프로젝트
 * Level: 골드-3
 **/
class Problem9466 {
    private lateinit var arr: IntArray
    private lateinit var visited: BooleanArray
    private lateinit var finished: BooleanArray
    private var count = 0

    fun solution(arr: IntArray): Int {
        this.arr = intArrayOf(0, *arr)
        this.visited = BooleanArray(arr.size + 1)
        this.finished = BooleanArray(arr.size + 1)

        for (i in 1..arr.size) {
            if (finished[i]) continue
            dfs(i)
        }
        return arr.size - count
    }

    private fun dfs(index: Int) {
        if (finished[index]) return

        if (visited[index]) {
            finished[index] = true
            count++
        }

        visited[index] = true
        dfs(arr[index])
        finished[index] = true
        visited[index] = false
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val t = br.readLine().toInt()

        repeat(t) {
            val n = br.readLine().toInt()
            val st = StringTokenizer(br.readLine())
            val arr = IntArray(n) { st.nextToken().toInt() }

            Problem9466().solution(arr).also { bw.write("$it") }
            bw.newLine()
        }
        bw.flush()
    }
}
