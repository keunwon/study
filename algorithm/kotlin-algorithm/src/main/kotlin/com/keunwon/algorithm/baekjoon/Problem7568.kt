package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem7568 {
    fun solution(peoples: Array<Pair<Int, Int>>): IntArray {
        val answer = mutableListOf<Int>()

        for (i in peoples.indices) {
            val target = peoples[i]
            var rank = 1

            for (j in peoples.indices) {
                if (target.first < peoples[j].first && target.second < peoples[j].second) {
                    ++rank
                }
            }
            answer.add(rank)
        }
        return answer.toIntArray()
    }
}

fun main() {
    val n = readln().toInt()
    val peoples = Array(n) {
        val st = StringTokenizer(readln())
        Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }

    println(Problem7568().solution(peoples).joinToString(" "))
}
