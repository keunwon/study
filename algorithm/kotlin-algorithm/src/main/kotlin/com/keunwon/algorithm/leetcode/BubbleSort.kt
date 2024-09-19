package com.keunwon.algorithm.leetcode

class BubbleSort {
    fun sort(nums: IntArray) {
        for (i in nums.lastIndex downTo 1) {
            for (j in 0 until i) {
                if (nums[j] > nums[j + 1]) {
                    val tmp = nums[j]
                    nums[j] = nums[j + 1]
                    nums[j + 1] = tmp
                }
            }
        }
    }
}

fun main() {
    val sortArr1 = run {
        val arr = intArrayOf(5, 4, 1, 3, 2)
        BubbleSort().sort(arr)
        arr
    }
    println(sortArr1.joinToString(", "))

    val sortArr2 = run {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        BubbleSort().sort(arr)
        arr
    }
    println(sortArr2.joinToString(", "))

    val sortArr3 = run {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        BubbleSort().sort(arr)
        arr
    }
    println(sortArr3.joinToString(", "))
}
