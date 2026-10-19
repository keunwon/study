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
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return when {
            p == null && q == null -> true
            p == null || q == null -> false
            p.`val` != q.`val` -> false
            else -> isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }
}