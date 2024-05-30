package com.keunwon.algorithm.programmers

class Lesson43165 {
    private var answer = 0

    fun solution(numbers: IntArray, target: Int): Int {
        dfs(0, 0, numbers, target)
        return answer
    }

    private fun dfs(index: Int, total: Int, numbers: IntArray, target: Int) {
        if (index == numbers.size) {
            if (total == target) ++answer
            return
        }

        dfs(index + 1, total + numbers[index], numbers, target)
        dfs(index + 1, total + -numbers[index], numbers, target)
    }
}
