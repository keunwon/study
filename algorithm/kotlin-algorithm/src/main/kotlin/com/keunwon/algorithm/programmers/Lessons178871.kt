package com.keunwon.algorithm.programmers

/**
 * Title: 달리기 경주
 * Level: 1
 **/
class Lessons178871 {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val rankMap = players.indices.associateBy { players[it] }.toMutableMap()

        callings.forEach { call ->
            val rank = rankMap.getValue(call)
            val tmp = players[rank - 1]

            players[rank - 1] = call
            rankMap[call] = rank - 1

            players[rank] = tmp
            rankMap[tmp] = rank
        }
        return players
    }
}
