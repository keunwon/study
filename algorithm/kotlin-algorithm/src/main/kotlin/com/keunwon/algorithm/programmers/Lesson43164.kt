package com.keunwon.algorithm.programmers

class Lesson43164 {
    private lateinit var tickets: Array<Array<String>>

    private val answer = mutableListOf<String>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        this.tickets = tickets
        dfs(0, "ICN", "ICN", BooleanArray(tickets.size))
        return answer.sorted()[0].split(" ").toTypedArray()
    }

    private fun dfs(depth: Int, route: String, start: String, visited: BooleanArray) {
        if (depth == tickets.size) {
            answer.add(route)
            return
        }

        tickets.forEachIndexed { index, (s, e) ->
            if (!visited[index] && start == s) {
                visited[index] = true
                dfs(depth + 1, "$route $e", e, visited)
                visited[index] = false
            }
        }
    }
}