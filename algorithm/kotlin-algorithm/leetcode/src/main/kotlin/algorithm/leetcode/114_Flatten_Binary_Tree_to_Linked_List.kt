package algorithm.leetcode

class `114_Flatten_Binary_Tree_to_Linked_List` {
    fun flatten(root: TreeNode?): Unit {
        dfs(root)
    }

    private fun dfs(node: TreeNode?): TreeNode? {
        if (node == null) return null
        if (node.left == null && node.right == null) return node

        val left = dfs(node.left)
        val right = dfs(node.right)

        if (left != null) {
            left.right = node.right
            node.right = node.left
            node.left = null
        }
        return right ?: left
    }
}

fun main() {
    val treeNode = listTreeNodeOf(1, 2, 5, 3, 4, null, 6)
    `114_Flatten_Binary_Tree_to_Linked_List`().flatten(treeNode)
    println(treeNode.printValues()) // 1,null,2,null,3,null,4,null,5,null,6
}
