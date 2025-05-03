package algorithm.leetcode

class `129_Sum_Root_to_Leaf_Numbers` {
    fun sumNumbers(root: TreeNode?): Int {
        return dfs(root, 0)
    }

    private fun dfs(node: TreeNode?, sum: Int): Int {
        if (node == null) return 0

        val num = sum * 10 + node.`val`
        if (node.left == null && node.right == null) return num
        return dfs(node.left, num) + dfs(node.right, num)
    }
}
