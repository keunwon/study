package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 숫자고르기
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2668">숫자고르기 (골드-5)</a>
 **/
class Problem2668 {
    private lateinit var numbers: IntArray
    private lateinit var visited: BooleanArray

    private var result = mutableListOf<Int>()

    fun solution(numbers: IntArray): IntArray {
        this.numbers = intArrayOf(0, *numbers)
        this.visited = BooleanArray(numbers.size + 1)

        for (i in 1..numbers.size) {
            dfs(i, i)
        }
        result.add(0, result.size)
        return result.toIntArray()
    }

    private fun dfs(index: Int, num: Int) {
        if (numbers[index] == num) {
            result.add(num)
            return
        }

        if (!visited[index]) {
            visited[index] = true
            dfs(numbers[index], num)
            visited[index] = false
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val numbers = IntArray(n) { br.readLine().toInt() }
    Problem2668().solution(numbers).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
