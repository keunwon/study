package com.keunwon.algorithm.againresolve

class ALessons43164 {
    private lateinit var tickets: Array<Array<String>>
    private lateinit var visited: BooleanArray
    private val answer = mutableListOf<String>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        this.tickets = tickets
        this.visited = BooleanArray(tickets.size)

        dfs(0, "ICN", "ICN")
        return answer.minOf { it }
            .split(" ")
            .toTypedArray()
    }

    private fun dfs(depth: Int, route: String, target: String) {
        if (depth == tickets.size) {
            answer.add(route)
            return
        }

        for ((i, ticket) in tickets.withIndex()) {
            val (src, dest) = ticket
            if (!visited[i] && target == src) {
                visited[i] = true
                dfs(depth + 1, "$route $dest", dest)
                visited[i] = false
            }
        }
    }
}

fun main() {
    ALessons43164().solution(
        arrayOf(
            arrayOf("ICN", "JFK"),
            arrayOf("HND", "IAD"),
            arrayOf("JFK", "HND")
        )
    ).forEach(::println)

    println("-------------------")

    ALessons43164().solution(
        arrayOf(
            arrayOf("ICN", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("SFO", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO")
        )
    ).forEach(::println)
}
