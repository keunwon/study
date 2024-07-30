package com.keunwon.algorithm.programmers

class Lesson178871 {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val ranks = players.indices.associateBy { players[it] }.toMutableMap()

        for (call in callings) {
            val index = ranks.getValue(call)
            val p1 = players[index - 1]
            val p2 = players[index]

            players[index] = p1
            players[index - 1] = p2

            ranks[p1] = index
            ranks[p2] = index - 1
        }
        return players
    }
}
