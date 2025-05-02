package algorithm.leetcode

class `39_Combination_Sum` {
    private val result = hashSetOf<List<Int>>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        dfs(candidates, target, 0, mutableListOf(), 0)
        return result.toList()
    }

    private fun dfs(
        candidates: IntArray,
        target: Int,
        sum: Int,
        list: MutableList<Int>,
        sIndex: Int,
    ) {
        if (sum > target) return
        if (sum == target) {
            result.add(list.toList())
            return
        }

        for (i in sIndex until candidates.size) {
            val candi = candidates[i]
            list.add(candi)
            dfs(candidates, target, sum + candi, list, i)
            list.remove(candi)
        }
    }
}
