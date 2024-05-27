package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem2304 {
    fun solution(building: Array<Pair<Int, Int>>): Int {
        building.sortWith(compareBy({ it.first }, { it.second }))

        var answer = 0
        var pre = building[0]

        for (i in 1 until building.size) {
            val cur = building[i]
            if (pre.second <= cur.second) {
                answer += (cur.first - pre.first) * pre.second
                pre = cur
            }
        }

        answer += pre.second
        pre = building.last()

        for (i in building.lastIndex - 1 downTo 0) {
            val cur = building[i]

            if (pre.second < cur.second) {
                answer += (pre.first - cur.first) * pre.second
                pre = cur
            }
        }
        return answer
    }
}

fun main() {
    val n = readln().toInt()
    val pillars = Array(n) {
        val st = StringTokenizer(readln())
        Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }

    println(Problem2304().solution(pillars))
}
