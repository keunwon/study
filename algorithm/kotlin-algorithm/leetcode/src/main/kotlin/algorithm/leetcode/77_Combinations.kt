package algorithm.leetcode

class `77_Combinations` {
    private val result = mutableListOf<List<Int>>()

    fun combine(n: Int, k: Int): List<List<Int>> {
        dfs(n, IntArray(k), 0, 1)
        return result
    }

    private fun dfs(n: Int, picks: IntArray, depth: Int, sIndex: Int) {
        if (depth == picks.size) {
            result.add(picks.toList())
            return
        }

        for (i in sIndex..n) {
            picks[depth] = i
            dfs(n, picks, depth + 1, i + 1)
        }
    }
}
