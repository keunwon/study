package com.keunwon.algorithm.leetcode

class `98_Validate_Binary_Search_Tree` {
    fun isValidBST(root: TreeNode?): Boolean {
        return dfs(root)
    }

    private fun dfs(node: TreeNode?): Boolean {
        if (node == null) return false
        if (node.left == null && node.right == null) return false

        val left = dfs(node.left)
        val right = dfs(node.right)
        return left == right
    }
}

fun main() {
//    `98_Validate_Binary_Search_Tree`().isValidBST(listTreeNodeOf(2, 1, 3))
//        .also { println(it) }

    `98_Validate_Binary_Search_Tree`().isValidBST(listTreeNodeOf(0))
        .also { println(it) }
}
