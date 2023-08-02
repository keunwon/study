package com.keunwon.algorithm.againresolve

class ALessons131703 {
    private lateinit var beginning: Array<IntArray>
    private lateinit var target: Array<IntArray>
    private var answer = Int.MAX_VALUE

    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        this.beginning = beginning
        this.target = target

        dfs(0, 0)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(depth: Int, count: Int) {
        if (depth == beginning.size) {
            var result = count
            var flag = true

            for (i in beginning[0].indices) {
                val num = beginning.indices.count { beginning[it][i] == target[it][i] }.let {
                    when (it) {
                        beginning.size -> 0
                        0 -> 1
                        else -> -1
                    }
                }
                if (num == -1) {
                    flag = false
                    break
                }
                result += num
            }
            if (flag) answer = answer.coerceAtMost(result)
            return
        }

        turn(depth)
        dfs(depth + 1, count + 1)
        turn(depth)
        dfs(depth + 1, count)
    }

    private fun turn(row: Int) {
        beginning[row].forEachIndexed { index, num -> beginning[row][index] = 1 xor num }
    }
}

fun main() {
    val beginning = arrayOf(
        intArrayOf(0, 1, 0, 0, 0),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(0, 1, 1, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 1, 0),
    )
    val target = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 1),
        intArrayOf(0, 0, 1, 0, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 1),
    )
    ALessons131703().solution(beginning, target).also { println(it) }
}
