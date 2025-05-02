package algorithm.leetcode

class `40_Combination_Sum_II` {
    private val result = mutableListOf<List<Int>>()

    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort()
        dfs(candidates, target, 0, mutableListOf(), 0)
        return result
    }

    private fun dfs(
        candidates: IntArray,
        target: Int,
        sIndex: Int,
        list: MutableList<Int>,
        cur: Int,
    ) {
        if (cur == target) {
            result.add(list.toList())
            return
        }

        var last = -1
        for (i in sIndex until candidates.size) {
            val candi = candidates[i]
            val sum = cur + candi

            if (last == candi) continue
            if (sum > target) break

            list.add(candi)
            dfs(candidates, target, i + 1, list, cur + candi)
            last = list.removeAt(list.lastIndex)
        }
    }
}
