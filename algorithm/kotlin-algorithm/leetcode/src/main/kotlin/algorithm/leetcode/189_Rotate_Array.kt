package algorithm.leetcode

class `189_Rotate_Array` {
    fun rotate(nums: IntArray, k: Int): Unit {
        val result = IntArray(nums.size)
        var idx = 0
        val ret = k % nums.size

        for (i in ret downTo 0) {
            result[idx++] = nums[nums.lastIndex - i]
        }

        for (i in 0 until nums.size - k) {
            result[idx++] = nums[i]
        }

        System.arraycopy(result, 0, nums, 0, nums.size)
    }
}
