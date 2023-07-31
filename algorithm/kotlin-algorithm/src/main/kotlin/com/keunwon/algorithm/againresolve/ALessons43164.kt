package com.keunwon.algorithm.againresolve

class ALessons43164 {
    private lateinit var tickets: Array<Array<String>>
    private lateinit var visited: BooleanArray
    private val answer = mutableListOf<String>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        this.tickets = tickets
        this.visited = BooleanArray(tickets.size)

        dfs(0, "ICN", "ICN")
        return answer.minOrNull()!!.split(" ").toTypedArray()
    }

    private fun dfs(depth: Int, target: String, route: String) {
        if (depth == tickets.size) {
            if (visited.all { it }) answer.add(route)
            return
        }

        for (i in tickets.indices) {
            val (src, dest) = tickets[i]

            if (!visited[i] && src == target) {
                visited[i] = true
                dfs(depth + 1, dest, "$route $dest")
                visited[i] = false
            }
        }
    }
}


fun main() {
    val tickets = arrayOf(
        arrayOf("ICN", "SFO"),
        arrayOf("ICN", "ATL"),
        arrayOf("SFO", "ATL"),
        arrayOf("ATL", "ICN"),
        arrayOf("ATL", "SFO"),
    )

    ALessons43164().solution(tickets).also { println(it.joinToString(", ")) }
}
