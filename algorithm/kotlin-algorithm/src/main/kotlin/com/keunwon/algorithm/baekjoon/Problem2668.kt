package com.keunwon.algorithm.baekjoon

/**
 * Title: 숫자고르기
 * Level: 골드-5
 **/
class Problem2668 {
    private lateinit var numbers: IntArray
    private val answer = mutableListOf<Int>()

    fun solution(arr: IntArray): List<Int> {
        this.numbers = intArrayOf(0, *arr)

        for (i in 1 until numbers.size) {
            dfs(i, i, BooleanArray(numbers.size))
        }
        return answer.sorted()
    }

    private fun dfs(index: Int, target: Int, visited: BooleanArray) {
        if (visited[index]) return

        if (numbers[index] == target) {
            answer.add(target)
            return
        }
        visited[index] = true
        dfs(numbers[index], target, visited)
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val arr = IntArray(n) { br.readLine().toInt() }

        val list = Problem2668().solution(arr)

        bw.write("${list.size}")
        bw.newLine()
        list.forEach {
            bw.write("$it")
            bw.newLine()
        }
        bw.flush()
        bw.close()
    }
}
