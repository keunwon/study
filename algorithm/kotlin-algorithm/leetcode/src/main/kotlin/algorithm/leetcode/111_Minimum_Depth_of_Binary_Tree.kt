package algorithm.leetcode

class `111_Minimum_Depth_of_Binary_Tree` {
    fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0
        if (root.left == null) return 1 + minDepth(root.right)
        if (root.right == null) return 1 + minDepth(root.left)
        return 1 + minDepth(root.left).coerceAtMost(minDepth(root.right))
    }
}
