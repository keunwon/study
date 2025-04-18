package algorithm.leetcode

class `22_Generate_Parentheses` {
    private val result = mutableListOf<String>()

    fun generateParenthesis(n: Int): List<String> {
        dfs(n, 0, 0, "")
        return result
    }

    private fun dfs(n: Int, left: Int, right: Int, str: String) {
        if (left >= n && right >= n) {
            result.add(str)
            return
        }

        if (left < n) dfs(n, left + 1, right, "$str(")
        if (left > right) dfs(n, left, right + 1, "$str)")
    }
}

fun main() {
    `22_Generate_Parentheses`().generateParenthesis(3).also { println(it.joinToString(", ")) }
    `22_Generate_Parentheses`().generateParenthesis(1).also { println(it.joinToString(", ")) }
}
