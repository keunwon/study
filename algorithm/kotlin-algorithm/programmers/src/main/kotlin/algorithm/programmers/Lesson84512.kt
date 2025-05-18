package algorithm.programmers

class Lesson84512 {
    private val alphabets = charArrayOf('A', 'E', 'I', 'O', 'U')
    private val answer = mutableListOf<String>()

    fun solution(word: String): Int {
        dfs(0, "", alphabets.size)
        println(answer.size)
        return answer.indexOf(word)
    }

    private fun dfs(depth: Int, word: String, n: Int) {
        answer.add(word)
        if (depth == n) return

        for (c in alphabets) {
            dfs(depth + 1, "$word$c", n)
        }
    }
}
