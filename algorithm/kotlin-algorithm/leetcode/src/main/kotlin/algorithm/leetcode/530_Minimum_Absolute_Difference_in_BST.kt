package algorithm.leetcode

import kotlin.math.abs

class `530_Minimum_Absolute_Difference_in_BST` {
    var prev: TreeNode? = null
    var min = Int.MAX_VALUE

    fun getMinimumDifference(root: TreeNode?): Int {
        dfs(root)
        return min
    }

    private fun dfs(node: TreeNode?) {
        if (node == null) return

        dfs(node.left)

        if (prev != null) {
            min = minOf(min, abs(prev!!.`val` - node.`val`))
        }
        prev = node
        dfs(node.right)
    }
}

fun main() {
    `530_Minimum_Absolute_Difference_in_BST`()
        .getMinimumDifference(listTreeNodeOf(4, 2, 6, 1, 3))
        .also { println(it) } // 1

    `530_Minimum_Absolute_Difference_in_BST`()
        .getMinimumDifference(listTreeNodeOf(1, 0, 48, null, null, 12, 49))
        .also { println(it) } // 1

    `530_Minimum_Absolute_Difference_in_BST`()
        .getMinimumDifference(listTreeNodeOf(236, 104, 701, null, 227, null, 911))
        .also { println(it) } // 9
}
