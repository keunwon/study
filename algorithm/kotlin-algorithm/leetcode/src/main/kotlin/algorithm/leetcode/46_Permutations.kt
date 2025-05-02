package algorithm.leetcode

class `46_Permutations` {
    private val result = mutableListOf<List<Int>>()

    fun permute(nums: IntArray): List<List<Int>> {
        dfs(nums, BooleanArray(nums.size), IntArray(nums.size), 0)
        return result
    }

    private fun dfs(
        nums: IntArray,
        visited: BooleanArray,
        picks: IntArray,
        depth: Int,
    ) {
        if (depth == picks.size) {
            result.add(picks.toList())
            return
        }

        for ((i, n) in nums.withIndex()) {
            if (!visited[i]) {
                visited[i] = true
                picks[depth] = n
                dfs(nums, visited, picks, depth + 1)
                visited[i] = false
            }
        }
    }
}
