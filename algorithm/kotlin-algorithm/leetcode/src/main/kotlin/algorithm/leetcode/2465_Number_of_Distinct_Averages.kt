package algorithm.leetcode

class `2465_Number_of_Distinct_Averages` {
    fun distinctAverages(nums: IntArray): Int {
        nums.sort()

        var left = 0
        var right = nums.lastIndex
        val set = mutableSetOf<Double>()

        while (left < right) {
            val avg = (nums[left].toDouble() + nums[right]) / 2
            set.add(avg)
            ++left
            --right
        }
        return set.size
    }
}
