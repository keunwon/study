package com.keunwon.algorithm.againresolve

class ALessons64064 {
    private val answer = mutableSetOf<String>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val visited = BooleanArray(user_id.size)
        val banned = banned_id.map { it.replace("*", ".").toRegex() }
        dfs(0, banned, visited, user_id)
        return answer.size
    }

    private fun dfs(depth: Int, banned: List<Regex>, visited: BooleanArray, user_id: Array<String>) {
        if (depth == banned.size) {
            answer.add(visited.joinToString(""))
            return
        }
        for ((i, user) in user_id.withIndex()) {
            if (!visited[i] && user.matches(banned[depth])) {
                visited[i] = true
                dfs(depth + 1, banned, visited, user_id)
                visited[i] = false
            }
        }
    }
}

fun main() {
    ALessons64064().solution(
        arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
        arrayOf("fr*d*", "abc1**")
    ).also(::println)

    ALessons64064().solution(
        arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
        arrayOf("*rodo", "*rodo", "******")
    ).also(::println)

    ALessons64064().solution(
        arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
        arrayOf("fr*d*", "*rodo", "******", "******")
    ).also(::println)
}
