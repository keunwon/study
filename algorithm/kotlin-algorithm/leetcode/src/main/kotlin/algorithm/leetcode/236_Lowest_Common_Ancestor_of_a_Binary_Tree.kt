package algorithm.leetcode

class `236_Lowest_Common_Ancestor_of_a_Binary_Tree` {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return null

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)

        return when {
            root == p || root == q -> root
            left != null && right != null -> root
            else -> left ?: right
        }
    }
}

fun main() {
    `236_Lowest_Common_Ancestor_of_a_Binary_Tree`().lowestCommonAncestor(
        listTreeNodeOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4),
        TreeNode(5),
        TreeNode(1)
    ).also { println(it?.`val`) } // 3

    `236_Lowest_Common_Ancestor_of_a_Binary_Tree`().lowestCommonAncestor(
        listTreeNodeOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4),
        TreeNode(5),
        TreeNode(4),
    ).also { println(it?.`val`) } // 5
}
