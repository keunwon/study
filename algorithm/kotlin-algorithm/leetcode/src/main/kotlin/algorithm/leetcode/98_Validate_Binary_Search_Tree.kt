package algorithm.leetcode

class `98_Validate_Binary_Search_Tree` {
    fun isValidBST(root: TreeNode?): Boolean {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    fun isValidBST(root: TreeNode?, min: Long, max: Long): Boolean {
        if (root == null) return true
        if (root.`val` !in min..max) return false
        return isValidBST(root.left, min, root.`val` - 1L) &&
                isValidBST(root.right, root.`val` + 1L, max)
    }
}

fun main() {
    `98_Validate_Binary_Search_Tree`().isValidBST(listTreeNodeOf(2, 1, 3))
        .also { println(it) } // expect: true

    `98_Validate_Binary_Search_Tree`().isValidBST(listTreeNodeOf(5, 1, 4, null, null, 3, 6))
        .also { println(it) } // expect: false
}
