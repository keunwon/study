package algorithm.leetcode

class `572_Subtree_of_Another_Tree` {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null || subRoot == null) return false
        if (root.`val` == subRoot.`val` && dfs(root, subRoot)) {
            return true
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
    }

    private fun dfs(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null && subRoot == null) return true
        if (root == null || subRoot == null) return false
        return root.`val` == subRoot.`val` && dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right)
    }
}
