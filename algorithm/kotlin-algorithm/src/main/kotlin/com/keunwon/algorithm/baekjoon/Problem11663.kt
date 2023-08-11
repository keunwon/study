package com.keunwon.algorithm.baekjoon

/**
 * Title: 선분 위의 점
 * Level: 실버-3
 **/
class Problem11663 {
    private lateinit var arr: IntArray

    fun solution(arr: IntArray, positions: Array<Pair<Int, Int>>): IntArray {
        this.arr = arr.apply { sort() }
        return positions.map { (start, end) ->
            val n1 = upperBound(start)
            val n2 = binarySearch(end)
            n2 - n1 + 1
        }.toIntArray()
    }

    private fun upperBound(target: Int): Int {
        var left = 0
        var right = arr.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (target <= arr[mid]) right = mid - 1
            else left = mid + 1
        }
        return left
    }

    private fun binarySearch(target: Int): Int {
        var left = 0
        var right = arr.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (arr[mid] <= target) left = mid + 1
            else right = mid - 1
        }
        return right
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val readIntArray = {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val (n, m) = readIntArray()
    val arr1 = readIntArray()
    val positions = Array(m) { readIntArray().let { it[0] to it[1] } }

    Problem11663().solution(arr1, positions).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
