package algorithm.leetcode

class `104_Maximum_Depth_of_Binary_Tree` {
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + maxDepth(root.left).coerceAtLeast(maxDepth(root.right))
    }
}
