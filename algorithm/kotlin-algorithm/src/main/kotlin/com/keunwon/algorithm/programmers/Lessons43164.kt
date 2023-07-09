package com.keunwon.algorithm.programmers

/**
 * Title: 여행경로
 * Level: 3
 **/
class Lessons43164 {
    private val answer = mutableListOf<String>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        dfs(0, "ICN", "ICN", BooleanArray(tickets.size), tickets)
        return answer.minOf { it }.split(" ").toTypedArray()
    }

    private fun dfs(depth: Int, start: String, route: String, visited: BooleanArray, tickets: Array<Array<String>>) {
        if (depth == tickets.size) {
            answer.add(route)
            return
        }

        for (i in tickets.indices) {
            val (src, dest) = tickets[i]

            if (src == start && !visited[i]) {
                visited[i] = true
                dfs(depth + 1, dest, "$route $dest", visited, tickets)
                visited[i] = false
            }
        }
    }
}
