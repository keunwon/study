package com.keunwon.algorithm.programmers

/**
 * <p>
 * 이름:비밀 코드 해독
 * 난이도:Level-2
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/388352">비밀 코드 해독 (Level-2)</a>
 **/
class Lesson388352 {
    private lateinit var q: Array<IntArray>
    private lateinit var ans: IntArray

    private var result = 0

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        this.q = q
        this.ans = ans

        dfs(0, 1, n, IntArray(5))
        return result
    }

    private fun dfs(
        depth: Int,
        sIndex: Int,
        n: Int,
        picks: IntArray,
    ) {
        if (depth == picks.size) {
            val numbers = IntArray(q.size) { i -> q[i].count { it in picks } }
            if (numbers.contentEquals(ans)) ++result

            return
        }

        for (i in sIndex..n) {
            picks[depth] = i
            dfs(depth + 1, i + 1, n, picks)
        }
    }
}
