package algorithm.leetcode

class SelectionSort {
    fun sort(nums: IntArray) {
        for (i in nums.indices) {
            var sIndex = i

            for (j in i + 1 until nums.size) {
                if (nums[sIndex] > nums[j]) {
                    sIndex = j
                }
            }

            val tmp = nums[i]
            nums[i] = nums[sIndex]
            nums[sIndex] = tmp
        }
    }
}

fun main() {
    val sortArr1 = run {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        SelectionSort().sort(arr)
        arr
    }
    println(sortArr1.joinToString(", "))
}
