class Solution {
    private val res = mutableListOf<String>()

    fun generateParenthesis(n: Int): List<String> {
        dfs(n, 0, 0, "")
        return res
    }

    private fun dfs(n: Int, left: Int, right: Int, str: String) {
        if (left == n && right == n) {
            res.add(str)
            return
        }

        if (n > left) {
            dfs(n, left + 1, right, "$str(")
        }

        if (left > right) {
            dfs(n, left, right + 1, "$str)")
        }
    }
}