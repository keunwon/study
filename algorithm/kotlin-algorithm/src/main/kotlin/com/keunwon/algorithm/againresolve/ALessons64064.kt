package com.keunwon.algorithm.againresolve

class ALessons64064 {
    private val answer = mutableSetOf<String>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val banned = banned_id.map { it.replace("*", ".").toRegex() }
        dfs(0, BooleanArray(user_id.size), user_id, banned)
        return answer.size
    }

    private fun dfs(depth: Int, visited: BooleanArray, userId: Array<String>, banned: List<Regex>) {
        if (depth == banned.size) {
            answer.add(visited.joinToString(""))
            return
        }

        for ((i, id) in userId.withIndex()) {
            if (!visited[i] && banned[depth].matches(id)) {
                visited[i] = true
                dfs(depth + 1, visited, userId, banned)
                visited[i] = false
            }
        }
    }
}

fun main() {
    val userId = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
    val bannedId = arrayOf("fr*d*", "abc1**")

    ALessons64064().solution(userId, bannedId).also { println(it) }
}
