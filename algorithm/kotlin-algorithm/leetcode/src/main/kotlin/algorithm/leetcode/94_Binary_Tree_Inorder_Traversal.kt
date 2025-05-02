package algorithm.leetcode

class `94_Binary_Tree_Inorder_Traversal` {
    private val result = mutableListOf<Int>()

    fun inorderTraversal(root: TreeNode?): List<Int> {
        dfs(root)
        return result
    }

    private fun dfs(cur: TreeNode?) {
        if (cur == null) return

        dfs(cur.left)
        result.add(cur.`val`)
        dfs(cur.right)
    }
}
