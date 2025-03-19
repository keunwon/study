package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 크로스 컨트리
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/9017">크로스 컨트리 (실버-3)</a>
 **/
class Problem9017 {
    fun solution(team: IntArray): Int {
        val countTeam = IntArray(201)
        val teamMap = mutableMapOf<Int, MutableList<Int>>()

        team.forEach {
            ++countTeam[it]
            if (countTeam[it] == 6) teamMap[it] = mutableListOf()
        }

        var rank = 1
        team.forEach { t -> teamMap[t]?.add(rank++) }

        return teamMap.toList()
            .sortedWith(compareBy({ it.second.take(4).sum() }, { it.second[4] }, { it.second[5] }))
            .first().first
    }
}

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val team = run {
            val arr = readln().split(" ")
            IntArray(n) { arr[it].toInt() }
        }

        Problem9017().solution(team).also { println(it) }
    }
}
