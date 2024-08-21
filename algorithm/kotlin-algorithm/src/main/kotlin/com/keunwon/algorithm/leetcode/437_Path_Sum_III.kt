package com.keunwon.algorithm.leetcode

class `437_Path_Sum_III` {
    var count = 0

    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) return 0

        dfs(root, mutableMapOf(), 0L, targetSum.toLong())
        return count
    }

    private fun dfs(node: TreeNode?, map: MutableMap<Long, Int>, sum: Long, target: Long) {
        if (node == null) return

        val nextSum = sum + node.`val`

        if (nextSum == target) ++count
        count += map.getOrDefault(nextSum - target, 0)
        map[nextSum] = map.getOrDefault(nextSum, 0) + 1

        dfs(node.left, map, nextSum, target)
        dfs(node.right, map, nextSum, target)

        map[nextSum] = map.getValue(nextSum) - 1
    }
}

fun main() {
    `437_Path_Sum_III`()
        .pathSum(listTreeNodeOf(10, 5, -3, -5, 2, null, null, 8, 100, null, 6), 8)
        .also { println(it) }
}
