package com.keunwon.algorithm.programmers

/**
 * Title: 불량 사용자
 * Level: 3
 **/
class Lessons64064 {
    private val answer = mutableSetOf<String>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val banned = banned_id.map { it.replace("*", ".").toRegex() }
        val visited = BooleanArray(user_id.size)

        dfs(0, visited, user_id, banned)
        return answer.size
    }

    private fun dfs(depth: Int, visited: BooleanArray, user_id: Array<String>, banned: List<Regex>) {
        if (depth == banned.size) {
            answer.add(visited.joinToString(""))
            return
        }

        for ((i, user) in user_id.withIndex()) {
            if (!visited[i] && user.matches(banned[depth])) {
                visited[i] = true
                dfs(depth + 1, visited, user_id, banned)
                visited[i] = false
            }
        }
    }
}
