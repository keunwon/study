package algorithm.leetcode

class InsertSort {
    fun sort(nums: IntArray) {
        for (i in 1 until nums.size) {
            val key = nums[i]
            var j = i - 1

            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j]
                --j
            }
            nums[j + 1] = key
        }
    }
}

fun main() {
    val sortArr1 = run {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        InsertSort().sort(arr)
        arr
    }
    println(sortArr1.joinToString(", "))

    val sortArr2 = run {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        InsertSort().sort(arr)
        arr
    }
    println(sortArr2.joinToString(", "))
}
