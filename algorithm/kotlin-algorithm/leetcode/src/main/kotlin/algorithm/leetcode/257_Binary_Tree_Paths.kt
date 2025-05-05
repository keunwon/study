package algorithm.leetcode

class `257_Binary_Tree_Paths` {
    private val result = mutableListOf<String>()

    fun binaryTreePaths(root: TreeNode?): List<String> {
        dfs(root, StringBuilder(201))
        return result
    }

    private fun dfs(node: TreeNode?, sb: StringBuilder) {
        if (node == null) return

        val length = sb.length
        sb.append(node.`val`)

        if (node.left == null && node.right == null) {
            result.add(sb.toString())
        } else {
            sb.append("->")
            dfs(node.left, sb)
            dfs(node.right, sb)
        }
        sb.setLength(length)
    }
}
