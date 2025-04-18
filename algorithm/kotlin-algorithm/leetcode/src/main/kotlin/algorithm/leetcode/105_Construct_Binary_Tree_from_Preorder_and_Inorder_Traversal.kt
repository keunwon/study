package algorithm.leetcode

class `105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal` {
    private lateinit var preorder: IntArray
    private lateinit var inorder: IntArray

    private var pIndex = 0

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        this.preorder = preorder
        this.inorder = inorder
        val result = dfs(0, inorder.lastIndex)

        return result
    }

    private fun dfs(left: Int, right: Int): TreeNode? {
        if (left > right) return null

        val node = TreeNode(preorder[pIndex])
        for (i in left..right) {
            if (preorder[pIndex] == inorder[i]) {
                ++pIndex
                node.left = dfs(left, i - 1)
                node.right = dfs(i + 1, right)
                break
            }
        }
        return node
    }
}


fun main() {
    `105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal`()
        .buildTree(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7))
}
