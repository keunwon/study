package algorithm.leetcode

class `106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal` {
    private lateinit var inorder: IntArray
    private lateinit var postorder: IntArray

    private var pIndex = 0

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        this.inorder = inorder
        this.postorder = postorder
        this.pIndex = postorder.lastIndex

        val result = dfs(0, inorder.lastIndex)
        return result
    }

    private fun dfs(left: Int, right: Int): TreeNode? {
        if (left > right) return null

        val node = TreeNode(postorder[pIndex])
        for (i in left..right) {
            if (postorder[pIndex] == inorder[i]) {
                --pIndex
                node.right = dfs(i + 1, right)
                node.left = dfs(left, i - 1)
                break
            }
        }
        return node
    }
}

fun main() {
    `106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal`()
        .buildTree(intArrayOf(9, 3, 15, 20, 7), intArrayOf(9, 15, 7, 20, 3))

    `106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal`()
        .buildTree(intArrayOf(-1), intArrayOf(-1))
}
