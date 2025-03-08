package com.keunwon.algorithm.programmers

/**
 * <p>
 * 이름: 금과 은 운반하기
 * 난이도: Level-3
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/86053">금과 은 운반하기 (Level-3)</a>
 **/
class Lesson86053 {
    fun solution(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Long {
        var left = 0L
        var right = (2e9 * 2e5).toLong()

        while (left <= right) {
            val time = left + (right - left) / 2

            if (check(time, a, b, g, s, w, t)) right = time - 1
            else left = time + 1
        }
        return left
    }

    private fun check(
        time: Long,
        a: Int,
        b: Int,
        g: IntArray,
        s: IntArray,
        w: IntArray,
        t: IntArray,
    ): Boolean {
        var sumGold = 0L
        var sumSilver = 0L
        var total = 0L

        for (i in t.indices) {
            val count = (time / t[i]).let { it / 2 + it % 2 }
            val gold = g[i].toLong()
            val silver = s[i].toLong()
            val weight = w[i] * count

            sumGold += weight.coerceAtMost(gold)
            sumSilver += weight.coerceAtMost(silver)
            total += weight.coerceAtMost(gold + silver)
        }
        return total >= a + b && sumGold >= a && sumSilver >= b
    }
}
