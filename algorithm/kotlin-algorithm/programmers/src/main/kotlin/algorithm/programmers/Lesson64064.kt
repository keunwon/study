package algorithm.programmers

class Lesson64064 {
    private lateinit var userIds: Array<String>
    private lateinit var banneds: List<Regex>
    private lateinit var visited: BooleanArray

    private val answer = mutableSetOf<String>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        this.userIds = user_id
        this.banneds = banned_id.map { it.replace("*", ".").toRegex() }
        this.visited = BooleanArray(user_id.size)

        dfs(0)
        return answer.size
    }

    private fun dfs(depth: Int) {
        if (depth == banneds.size) {
            answer.add(visited.joinToString(""))
            return
        }

        for ((i, user) in userIds.withIndex()) {
            if (!visited[i] && banneds[depth].matches(user)) {
                visited[i] = true
                dfs(depth + 1)
                visited[i] = false
            }
        }
    }
}
