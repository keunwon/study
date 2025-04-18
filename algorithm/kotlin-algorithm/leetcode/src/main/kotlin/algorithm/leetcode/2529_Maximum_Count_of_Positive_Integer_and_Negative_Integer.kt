package algorithm.leetcode

class `2529_Maximum_Count_of_Positive_Integer_and_Negative_Integer` {
    fun maximumCount(nums: IntArray): Int {
        val neg = binarySearch(nums, 0)
        val pos = nums.size - binarySearch(nums, 1)
        return neg.coerceAtLeast(pos)
    }

    private fun binarySearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (nums[mid] < target) left = mid + 1
            else right = mid - 1
        }
        return left
    }
}

fun main() {
    `2529_Maximum_Count_of_Positive_Integer_and_Negative_Integer`()
        .maximumCount(intArrayOf(-2, -1, -1, 1, 2, 3))
        .also { println(it) } // 3

    `2529_Maximum_Count_of_Positive_Integer_and_Negative_Integer`()
        .maximumCount(intArrayOf(-3, -2, -1, 0, 0, 1, 2))
        .also { println(it) } // 3

    `2529_Maximum_Count_of_Positive_Integer_and_Negative_Integer`()
        .maximumCount(intArrayOf(5, 20, 66, 1314))
        .also { println(it) } // 4
}
