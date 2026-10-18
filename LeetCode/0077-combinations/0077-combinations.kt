class Solution {
    private val res = mutableListOf<List<Int>>()

    fun combine(n: Int, k: Int): List<List<Int>> {
        dfs(IntArray(k), n, 1, 0)
        return res
    }

    private fun dfs(pick: IntArray, n: Int, startNum: Int, depth: Int) {
        if (depth == pick.size) {
            res.add(pick.toList())
            return
        }

        for (i in startNum..n) {
            pick[depth] = i
            dfs(pick, n, i + 1, depth + 1)
        }
    }
}