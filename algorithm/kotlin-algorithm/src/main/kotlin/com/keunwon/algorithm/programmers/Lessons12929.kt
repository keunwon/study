package com.keunwon.algorithm.programmers

/**
 * Title: 올바른 괄호의 갯수
 * Level: 4
 **/
class Lessons12929 {
    private var answer = 0

    fun solution(n: Int): Int {
        dfs(0, 0, n)
        return answer
    }

    private fun dfs(left: Int, right: Int, n: Int) {
        if (left < right) return
        if (left > n || right > n) return
        if (left == n && right == n) {
            ++answer
            return
        }
        dfs(left, right + 1, n)
        dfs(left + 1, right, n)
    }
}
