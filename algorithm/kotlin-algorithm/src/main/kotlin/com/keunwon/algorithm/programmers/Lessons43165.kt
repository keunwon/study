package com.keunwon.algorithm.programmers

/**
 * Title: 타겟 넘버
 * Level: 2
 **/
class Lessons43165 {
    private var answer = 0

    fun solution(numbers: IntArray, target: Int): Int {
        dfs(0, 0, numbers, target)
        return answer
    }

    private fun dfs(depth: Int, num: Int, numbers: IntArray, target: Int) {
        if (depth == numbers.size) {
            if (num == target) answer++
            return
        }

        dfs(depth + 1, num + numbers[depth], numbers, target)
        dfs(depth + 1, num - numbers[depth], numbers, target)
    }
}
