package algorithm.leetcode

import java.util.LinkedList

class `637_Average_of_Levels_in_Binary_Tree` {
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()

        val result = mutableListOf<Double>()
        val q = LinkedList<TreeNode>().apply { offer(root) }

        while (q.isNotEmpty()) {
            val size = q.size
            var sum = 0.0

            repeat(size) {
                val cur = q.pop()
                sum += cur.`val`
                if (cur.left != null) q.offer(cur.left)
                if (cur.right != null) q.offer(cur.right)
            }
            result.add(sum / size)
        }
        return result.toDoubleArray()
    }
}
