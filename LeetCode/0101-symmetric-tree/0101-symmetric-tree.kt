/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        return isSame(root.left, root.right)
    }

    private fun isSame(left: TreeNode?, right: TreeNode?): Boolean {
        return when {
            left == null && right == null -> true
            left == null || right == null -> false
            left.`val` != right.`val` -> false
            else -> isSame(left.left, right.right) && isSame(left.right, right.left)
        }
    }
}