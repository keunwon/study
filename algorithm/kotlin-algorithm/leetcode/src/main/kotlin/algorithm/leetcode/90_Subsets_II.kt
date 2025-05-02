package algorithm.leetcode

class `90_Subsets_II` {
    private val result = mutableListOf<List<Int>>()

    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        dfs(nums, mutableListOf(), 0)
        return result
    }

    private fun dfs(nums: IntArray, picks: MutableList<Int>, sIndex: Int) {
        result.add(picks.toList())

        for (i in sIndex until nums.size) {
            if (i > sIndex && nums[i] == nums[i - 1]) continue

            picks.add(nums[i])
            dfs(nums, picks, i + 1)
            picks.removeAt(picks.lastIndex)
        }
    }
}
