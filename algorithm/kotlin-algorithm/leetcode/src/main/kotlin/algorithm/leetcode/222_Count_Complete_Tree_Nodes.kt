package algorithm.leetcode

class `222_Count_Complete_Tree_Nodes` {
    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + countNodes(root.left) + countNodes(root.right)
    }
}
