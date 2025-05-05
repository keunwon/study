package algorithm.leetcode

import kotlin.math.abs

class `110_Balanced_Binary_Tree` {
    fun isBalanced(root: TreeNode?): Boolean {
        return dfs(root) > -1
    }

    private fun dfs(root: TreeNode?): Int {
        if (root == null) return 0

        val left = dfs(root.left)
        if (0 > left) return -1

        val right = dfs(root.right)
        if (0 > right || abs(left - right) > 1) return -1

        return 1 + left.coerceAtLeast(right)
    }
}
