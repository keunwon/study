package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem9017 {
    fun solution(teams: IntArray): Int {
        val keys = teams.toList()
            .groupingBy { it }
            .eachCount()
            .filter { it.value == 6 }.keys
        val ranks = mutableMapOf<Int, MutableList<Int>>()
        var rank = 1

        for (team in teams) {
            if (team in keys) {
                val list = ranks[team] ?: mutableListOf()
                ranks[team] = list.apply { add(rank++) }
            }
        }

        return ranks.entries
            .sortedWith(compareBy({ it.value.take(4).sum() }, { it.value[4] }))
            .first().key
    }
}

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val teams = run {
            val st = StringTokenizer(readln())
            IntArray(n) { st.nextToken().toInt() }
        }

        println(Problem9017().solution(teams))
    }
}
