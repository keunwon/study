package com.keunwon.algorithm.baekjoon

/**
 * Title: IF문 좀 대신 써줘
 * Level: 실버-3
 **/
class Problem19637 {
    fun solution(arr: Array<Pair<String, Int>>, numbers: IntArray): Array<String> {
        arr.sortBy { it.second }
        return numbers.map { num ->
            val findIndex = binarySearch(arr, num)
            arr[findIndex].first
        }.toTypedArray()
    }

    private fun binarySearch(arr: Array<Pair<String, Int>>, num: Int): Int {
        var left = 0
        var right = arr.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (num <= arr[mid].second) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { br.readLine().split(" ").let { (a, b) -> a to b.toInt() } }
    val numbers = IntArray(m) { br.readLine().toInt() }

    Problem19637().solution(arr, numbers).forEach { bw.write("$it\n") }

    bw.flush()
    bw.close()
}
