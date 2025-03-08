package com.keunwon.algorithm.programmers

import java.util.*

/**
 * <p>
 * 이름:[PCCP 기출문제] 3번 / 충돌위험 찾기
 * 난이도: Level-2
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/340211">[PCCP 기출문제] 3번 / 충돌위험 찾기 (Level-2)</a>
 **/
class Lesson340211 {
    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        val map = points.withIndex().associate { (idx, arr) -> Pair(idx + 1, Pair(arr[0], arr[1])) }
        val history = Array(routes.size) { LinkedList<Pair<Int, Int>>() }
        var max = 0

        for ((i, route) in routes.withIndex()) {
            for (j in 1 until route.size) {
                var (sr, sc) = map.getValue(route[j - 1])
                val (dr, dc) = map.getValue(route[j])

                if (history[i].isEmpty()) {
                    history[i].offer(sr to sc)
                }

                while (sr != dr) {
                    if (sr < dr) ++sr else --sr
                    history[i].offer(sr to sc)
                }

                while (sc != dc) {
                    if (sc < dc) ++sc else --sc
                    history[i].offer(sr to sc)
                }
            }

            max = max.coerceAtLeast(history[i].size)
        }

        var result = 0
        for (i in 0 until max) {
            val tmpMap = mutableMapOf<Pair<Int, Int>, Int>()
            for (q in history) {
                val p = if (q.isNotEmpty()) q.poll() else continue
                tmpMap[p] = tmpMap.getOrDefault(p, 0) + 1
            }
            result += tmpMap.values.count { it >= 2 }
        }
        return result
    }
}
