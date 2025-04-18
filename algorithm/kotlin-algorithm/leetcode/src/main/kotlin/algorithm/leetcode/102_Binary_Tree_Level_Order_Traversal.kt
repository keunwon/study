package algorithm.leetcode

class `102_Binary_Tree_Level_Order_Traversal` {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        return mutableListOf<MutableList<Int>>().apply { dfs(root, 0, this) }
    }

    private fun dfs(node: TreeNode?, depth: Int, result: MutableList<MutableList<Int>>) {
        if (node == null) return

        if (depth == result.size) {
            result.add(mutableListOf())
        }
        result[depth].add(node.`val`)

        dfs(node.left, depth + 1, result)
        dfs(node.right, depth + 1, result)
    }
}
