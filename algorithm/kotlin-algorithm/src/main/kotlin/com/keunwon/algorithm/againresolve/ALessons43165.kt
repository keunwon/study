package com.keunwon.algorithm.againresolve

class ALessons43165 {
    private var answer = 0

    fun solution(numbers: IntArray, target: Int): Int {
        dfs(0, 0, numbers, target)
        return answer
    }

    private fun dfs(index: Int, sum: Int, numbers: IntArray, target: Int) {
        if (index == numbers.size) {
            if (sum == target) ++answer
            return
        }
        dfs(index + 1, sum - numbers[index], numbers, target)
        dfs(index + 1, sum + numbers[index], numbers, target)
    }
}

fun main() {
    ALessons43165().solution(intArrayOf(1, 1, 1, 1, 1), 3).also(::println)
    ALessons43165().solution(intArrayOf(4, 1, 2, 1), 4).also(::println)
}
