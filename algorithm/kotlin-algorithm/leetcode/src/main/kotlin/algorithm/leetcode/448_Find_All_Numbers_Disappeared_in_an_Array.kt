package algorithm.leetcode

class `448_Find_All_Numbers_Disappeared_in_an_Array` {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        val visited = BooleanArray(nums.size + 1).apply {
            this[0] = true
            nums.forEach { this[it] = true }
        }
        return visited.indices.filter { !visited[it] }
    }
}
