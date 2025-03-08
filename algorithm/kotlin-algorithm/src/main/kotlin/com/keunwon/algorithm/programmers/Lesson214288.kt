package com.keunwon.algorithm.programmers

import java.util.PriorityQueue

/**
 * <p>
 * 이름: 상담원 인원
 * 난이도: Level-3
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/214288">상담원 인원 (Level-3)</a>
 **/
class Lesson214288 {
    private lateinit var reqs: Array<IntArray>
    private var result = 1e9.toInt()

    fun solution(k: Int, n: Int, reqs: Array<IntArray>): Int {
        this.reqs = reqs
        dfs(n - k, 1, IntArray(k + 1) { 1 })
        return result
    }

    private fun dfs(remain: Int, sIndex: Int, mentors: IntArray) {
        if (remain <= 0) {
            val q = Array(mentors.size) { PriorityQueue<Int>() }
            var waitTime = 0

            for ((a, b, c) in reqs) {
                if (q[c].size < mentors[c]) {
                    q[c].offer(a + b)
                } else {
                    val time = q[c].poll()
                    waitTime += (time - a).coerceAtLeast(0)
                    q[c].offer(time.coerceAtLeast(a) + b)
                }
            }

            result = result.coerceAtMost(waitTime)
            return
        }

        for (i in sIndex until mentors.size) {
            ++mentors[i]
            dfs(remain - 1, i, mentors)
            --mentors[i]
        }
    }
}
