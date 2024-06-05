package com.keunwon.algorithm.programmers

// todo 다시풀기
class Lesson258709 {
    private lateinit var dice: Array<IntArray>

    private var answer = intArrayOf()
    private var max = 0

    fun solution(dice: Array<IntArray>): IntArray {
        this.dice = dice
        dfs(0, 0, BooleanArray(dice.size))
        return answer
    }

    private fun dfs(depth: Int, cur: Int, visited: BooleanArray) {
        if (depth == visited.size / 2) {
            val diceA = visited.indices.filter { visited[it] }.map { dice[it] }
            val diceB = visited.indices.filter { !visited[it] }.map { dice[it] }
            val sumA = mutableListOf<Int>().apply { calculateTo(this, 0, 0, diceA) }.sorted()
            val sumB = mutableListOf<Int>().apply { calculateTo(this, 0, 0, diceB) }.sorted()
            val win = sumA.sumOf { n -> binarySearch(n, sumB) + 1 }

            if (max < win) {
                max = win
                answer = visited.indices
                    .mapNotNull { if (visited[it]) it + 1 else null }
                    .toIntArray()
            }
            return
        }

        for (i in cur until visited.size) {
            if (!visited[i]) {
                visited[i] = true
                dfs(depth + 1, i, visited)
                visited[i] = false
            }
        }
    }

    private fun binarySearch(max: Int, list: List<Int>): Int {
        var left = 0
        var right = list.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (list[mid] < max) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun calculateTo(destination: MutableList<Int>, depth: Int, sum: Int, dices: List<IntArray>) {
        if (depth == dices.size) {
            destination.add(sum)
            return
        }

        for (i in dices[0].indices) {
            calculateTo(destination, depth + 1, sum + dices[depth][i], dices)
        }
    }
}
