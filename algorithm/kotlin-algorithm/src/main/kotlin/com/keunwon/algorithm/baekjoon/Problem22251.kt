package com.keunwon.algorithm.baekjoon

class Problem22251 {
    private val lights = arrayOf(
        intArrayOf(1, 1, 1, 0, 1, 1, 1),
        intArrayOf(0, 0, 1, 0, 0, 0, 1),
        intArrayOf(0, 1, 1, 1, 1, 1, 0),
        intArrayOf(0, 1, 1, 1, 0, 1, 1),
        intArrayOf(1, 0, 1, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 1, 0, 1, 1),
        intArrayOf(1, 1, 0, 1, 1, 1, 1),
        intArrayOf(0, 1, 1, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 0, 1, 1),
    )

    fun solution(n: Int, k: Int, p: Int, x: Int): Int {
        val targetArr = toArray(k, x)
        var answer = 0

        for (i in 1..n) {
            if (i == x) continue

            val tmpArr = toArray(k, i)
            val diff = (0 until k).sumOf { j ->
                val arr1 = lights[targetArr[j]]
                val arr2 = lights[tmpArr[j]]
                arr1.zip(arr2).count { it.first != it.second }
            }

            if (diff <= p) ++answer
        }
        return answer
    }

    private fun toArray(length: Int, n: Int): IntArray {
        val arr = IntArray(length)
        var ret = n

        for (i in arr.lastIndex downTo 0) {
            arr[i] = ret % 10
            ret /= 10
        }
        return arr
    }
}

fun main() {
    val (n, k, p, x) = readln().split(" ").map { it.toInt() }
    println(Problem22251().solution(n, k, p, x))
}
