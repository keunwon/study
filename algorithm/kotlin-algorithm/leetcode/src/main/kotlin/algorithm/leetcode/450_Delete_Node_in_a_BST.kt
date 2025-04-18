package algorithm.leetcode

class `450_Delete_Node_in_a_BST` {
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null

        if (root.`val` > key) {
            root.left = deleteNode(root.left, key)
        } else if (root.`val` < key) {
            root.right = deleteNode(root.right, key)
        } else {
            if (root.left == null) return root.right
            else if (root.right == null) return root.left

            root.`val` = minValue(root.right!!)
            root.right = deleteNode(root.right, root.`val`)
        }
        return root
    }

    private fun minValue(node: TreeNode): Int {
        var cur = node
        while (cur.left != null) {
            cur = cur.left!!
        }
        return cur.`val`
    }
}

fun main() {
    `450_Delete_Node_in_a_BST`()
        .deleteNode(listTreeNodeOf(5, 3, 6, 2, 4, null, 7), 3)
        .also { it.printValues() } // 5,4,6,2,null,null,7

    `450_Delete_Node_in_a_BST`()
        .deleteNode(listTreeNodeOf(), 0)
        .also { it.printValues() } // []
}
