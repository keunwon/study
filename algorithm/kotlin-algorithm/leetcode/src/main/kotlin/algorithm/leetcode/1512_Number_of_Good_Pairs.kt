package algorithm.leetcode

class `1512_Number_of_Good_Pairs` {
    fun numIdenticalPairs(nums: IntArray): Int {
        val map = HashMap<Int, Int>(nums.size).apply {
            nums.forEach { this[it] = getOrDefault(it, 0) + 1 }
        }
        return map.values.sumOf { (it * it - 1) / 2 }
    }
}
