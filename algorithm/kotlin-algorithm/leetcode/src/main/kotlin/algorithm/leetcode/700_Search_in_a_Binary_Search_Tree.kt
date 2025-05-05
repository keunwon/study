package algorithm.leetcode

class `700_Search_in_a_Binary_Search_Tree` {
    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) return null
        if (root.`val` == `val`) return root
        return if (root.`val` > `val`) {
            searchBST(root.left, `val`)
        } else {
            searchBST(root.right, `val`)
        }
    }
}
