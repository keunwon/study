package algorithm.leetcode

class `75_Sort_Colors` {
    fun sortColors(nums: IntArray): Unit {
        val map = mutableMapOf<Int, Int>().apply {
            nums.forEach { this[it] = getOrDefault(it, 0) + 1 }
        }
        var idx = 0

        for (i in 0 until 3) {
            val count = map[i] ?: continue
            repeat(count) { nums[idx++] = i }
        }
    }
}
