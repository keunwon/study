package com.keunwon.algorithm.programmers

/**
 * <p>
 * 이름:서버 증설 횟수
 * 난이도:Level-2
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/389479">서버 증설 횟수 (Level-2)</a>
 **/
class Lesson389479 {
    fun solution(players: IntArray, m: Int, k: Int): Int {
        var result = 0
        val servers = IntArray(players.size)

        for ((i, player) in players.withIndex()) {
            if (i > 0) servers[i] += servers[i - 1]

            if (player / m > servers[i]) {
                val add = player / m - servers[i]
                servers[i] += add
                if (i + k < servers.size) {
                    servers[i + k] -= add
                }

                result += add
            }
        }

        return result
    }
}
