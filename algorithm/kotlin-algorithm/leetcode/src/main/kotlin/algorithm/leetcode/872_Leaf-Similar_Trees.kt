package algorithm.leetcode

class `872_Leaf-Similar_Trees` {
    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        val list1 = mutableListOf<Int>().apply { dfs(root1, this) }
        val list2 = mutableListOf<Int>().apply { dfs(root2, this) }
        return list1.size == list2.size && list1.withIndex().all { it.value == list2[it.index] }
    }

    private fun dfs(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return
        if (root.left == null && root.right == null) {
            list.add(root.`val`)
            return
        }
        dfs(root.left, list)
        dfs(root.right, list)
    }
}
