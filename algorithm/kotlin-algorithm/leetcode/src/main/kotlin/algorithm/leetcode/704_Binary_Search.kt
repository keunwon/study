package algorithm.leetcode

class `704_Binary_Search` {
    fun search(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.lastIndex

        while (l <= r) {
            val mid = (l + r) / 2

            if (nums[mid] == target) return mid

            if (nums[mid] < target) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return -1
    }
}
