package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 소가 길을 건너간 이유 1
 * Level: 브론즈-1
 **/
class Problem14467 {
    fun solution(positions: Array<Pair<Int, Int>>): Int {
        val map = mutableMapOf<Int, Int>()
        var answer = 0

        for ((num, p) in positions) {
            if (!map.contains(num)) {
                map[num] = p
                continue
            }
            if (map.getValue(num) != p) {
                map[num] = p
                answer++
            }
        }
        return answer
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val positions = Array(n) {
            val st = StringTokenizer(br.readLine())
            Pair(st.nextToken().toInt(), st.nextToken().toInt())
        }

        Problem14467().solution(positions).also { bw.write("$it") }
        bw.flush()
    }
}
