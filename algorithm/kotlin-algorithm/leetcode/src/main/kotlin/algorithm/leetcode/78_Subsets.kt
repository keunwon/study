package algorithm.leetcode

class `78_Subsets` {
    private val result = mutableListOf<List<Int>>()

    fun subsets(nums: IntArray): List<List<Int>> {
        dfs(nums, mutableListOf(), 0)
        return result
    }

    private fun dfs(nums: IntArray, picks: MutableList<Int>, sIndex: Int) {
        result.add(picks.toList())

        for (i in sIndex until nums.size) {
            picks.add(nums[i])
            dfs(nums, picks, i + 1)
            picks.removeAt(picks.lastIndex)
        }
    }
}
