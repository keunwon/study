/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()

        val q = ArrayDeque<TreeNode>().apply { addLast(root) }
        val res = mutableListOf<Double>()

        while (q.isNotEmpty()) {
            val size = q.size
            var total = 0L

            for (i in 0 until size) {
                val cur = q.removeFirst()
                total += cur.`val`

                if (cur.left != null) q.addLast(cur.left) 
                if (cur.right != null) q.addLast(cur.right) 
            }
            res.add(total.toDouble() / size)
        }
        return res.toDoubleArray()
    }
}