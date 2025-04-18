package algorithm.leetcode

class `653_Two_Sum_IV_Input_is_a_BST` {
    val set = mutableSetOf<Int>()

    fun findTarget(root: TreeNode?, k: Int): Boolean {
        dfs(root)
        return set.any { n ->
            val target = k - n
            target != n && set.contains(target)
        }
    }

    private fun dfs(node: TreeNode?) {
        if (node == null) return

        set.add(node.`val`)
        dfs(node.left)
        dfs(node.right)
    }
}
