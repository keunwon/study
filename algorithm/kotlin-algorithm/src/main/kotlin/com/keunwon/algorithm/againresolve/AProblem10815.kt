package com.keunwon.algorithm.againresolve

/**
 * Title: 숫자 카드
 * Level: 실버-5
 **/
class AProblem10815 {
    fun solution(arr1: IntArray, arr2: IntArray): IntArray {
        arr1.sort()
        return arr2.map { if (binarySearch(arr1, it)) 1 else 0 }
            .toIntArray()
    }

    private fun binarySearch(arr: IntArray, target: Int): Boolean {
        var left = 0
        var right = arr.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            when {
                arr[mid] == target -> return true
                arr[mid] < target -> left = mid + 1
                arr[mid] > target -> right = mid - 1
            }
        }
        return false
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr1 = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val m = readLine()!!.toInt()
    val arr2 = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    AProblem10815().solution(arr1, arr2).also {
        println(it.joinToString(" "))
    }
}
