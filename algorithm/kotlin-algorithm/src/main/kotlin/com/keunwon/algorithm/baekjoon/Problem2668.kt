package com.keunwon.algorithm.baekjoon

class Problem2668 {
    private lateinit var numbers: IntArray
    private val answer = mutableListOf<Int>()

    fun solution(arr: IntArray): IntArray {
        this.numbers = intArrayOf(0, *arr)

        for (i in 1 until numbers.size) {
            val visited = BooleanArray(numbers.size)
            dfs(i, i, visited)
        }
        answer.add(0, answer.size)
        return answer.toIntArray()
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

fun main() {
    val n = readln().toInt()
    val arr = IntArray(n) { readln().toInt() }

    Problem2668().solution(arr).also { println(it.joinToString(System.lineSeparator())) }
}
