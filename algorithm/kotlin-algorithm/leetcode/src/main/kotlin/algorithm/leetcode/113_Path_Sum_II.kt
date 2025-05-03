package algorithm.leetcode

class `113_Path_Sum_II` {
    private val result = mutableListOf<List<Int>>()

    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        dfs(root, targetSum, 0, mutableListOf())
        return result
    }

    private fun dfs(
        node: TreeNode?,
        targetSum: Int,
        curSum: Int,
        target: MutableList<Int>,
    ) {
        if (node == null) return

        target.add(node.`val`)

        val sum = curSum + node.`val`

        if (node.left == null && node.right == null && sum == targetSum) {
            result.add(target.toList())
        }

        if (node.left != null) dfs(node.left, targetSum, sum, target)
        if (node.right != null) dfs(node.right, targetSum, sum, target)
        target.removeAt(target.lastIndex)
    }
}
