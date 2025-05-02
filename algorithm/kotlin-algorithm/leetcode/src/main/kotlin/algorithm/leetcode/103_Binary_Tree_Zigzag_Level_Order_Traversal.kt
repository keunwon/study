package algorithm.leetcode

import java.util.LinkedList

class `103_Binary_Tree_Zigzag_Level_Order_Traversal` {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return listOf()

        val q = LinkedList<TreeNode>().apply { offer(root) }
        val result = mutableListOf<List<Int>>()
        var isReverse = false

        while (q.isNotEmpty()) {
            val size = q.size
            val numbers = ArrayList<Int>(size)

            repeat(size) {
                val cur = q.poll()
                numbers.add(cur.`val`)
                if (cur.left != null) q.offer(cur.left)
                if (cur.right != null) q.offer(cur.right)
            }

            if (isReverse) numbers.reverse()
            result.add(numbers)
            isReverse = !isReverse
        }
        return result
    }
}

fun main() {
    `103_Binary_Tree_Zigzag_Level_Order_Traversal`()
        .zigzagLevelOrder(listTreeNodeOf(3, 9, 20, null, null, 15, 7))
        .also { it.printlnValues() } // expect: [[3],[20,9],[15,7]]
}
