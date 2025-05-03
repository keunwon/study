package algorithm.leetcode

import java.util.LinkedList

class `199_Binary_Tree_Right_Side_View` {
    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return listOf()

        val q = LinkedList<TreeNode>().apply { offer(root) }
        val result = mutableListOf(root.`val`)

        while (q.isNotEmpty()) {
            var last: TreeNode? = null

            repeat(q.size) {
                val cur = q.poll()
                last = cur
                if (cur.left != null) q.offer(cur.left)
                if (cur.right != null) q.offer(cur.right)
            }
            if (last != null) result.add(last.`val`)
        }
        return result
    }
}
