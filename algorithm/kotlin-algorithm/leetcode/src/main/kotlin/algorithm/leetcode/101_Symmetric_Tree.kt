package algorithm.leetcode

class `101_Symmetric_Tree` {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        return isSymmetric(root.left, root.right)
    }

    private fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false
        if (left.`val` != right.`val`) return false
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left)
    }
}
