class Solution {
    private lateinit var candidates: IntArray
    private val res = mutableListOf<List<Int>>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        this.candidates = candidates.apply { sort() }
        dfs(mutableListOf(), target, 0, 0)
        return res
    }

    private fun dfs(pick: MutableList<Int>, target: Int, curSum: Int, startIdx: Int) {
        if (curSum == target) {
            res.add(pick.toList())
            return
        }

        for (i in startIdx until candidates.size) {
            val nextSum = curSum + candidates[i]
            if (nextSum > target) break

            pick.add(candidates[i])
            dfs(pick, target, nextSum, i)
            pick.removeLast()
        }
    }
}