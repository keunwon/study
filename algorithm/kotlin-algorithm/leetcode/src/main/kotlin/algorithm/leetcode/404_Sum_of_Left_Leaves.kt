package algorithm.leetcode

class `404_Sum_of_Left_Leaves` {
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        return dfs(root, false)
    }

    private fun dfs(root: TreeNode?, isLeft: Boolean): Int {
        if (root == null) return 0
        if (root.left == null && root.right == null) {
            return if (isLeft) root.`val` else 0
        }
        return dfs(root.left, true) + dfs(root.right, false)
    }
}

fun main() {
    `404_Sum_of_Left_Leaves`().sumOfLeftLeaves(listTreeNodeOf(3, 9, 20, null, null, 15, 7))
        .also { println(it) }
}
