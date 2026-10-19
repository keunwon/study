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
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return listOf()

        val q = ArrayDeque<TreeNode>().apply { addLast(root) }
        val res = mutableListOf<List<Int>>()

        while (q.isNotEmpty()) {
            val list = mutableListOf<Int>()

            repeat(q.size) { _ ->
                val cur = q.removeFirst()
                list.add(cur.`val`)

                cur.left?.let { q.addLast(it) }
                cur.right?.let { q.addLast(it) }
            }
            res.add(list)
        }
        return res
    }
}