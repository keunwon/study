package com.keunwon.algorithm.againresolve

class AProblem19637 {
    private lateinit var words: Array<String>
    private lateinit var points: IntArray

    fun solution(arr1: Array<String>, arr2: IntArray): Array<String> {
        this.words = Array(arr1.size) { "" }
        this.points = IntArray(arr1.size)

        arr1.forEachIndexed { index, text ->
            val (word, point) = text.split(" ")
            words[index] = word
            points[index] = point.toInt()
        }

        return arr2.map { words[binarySearch(it)] }.toTypedArray()
    }

    private fun binarySearch(num: Int): Int {
        var left = 0
        var right = points.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (num <= points[mid]) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr1 = Array(n) { readLine() }
    val arr2 = IntArray(m) { readLine().toInt() }

    AProblem19637().solution(arr1, arr2).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
