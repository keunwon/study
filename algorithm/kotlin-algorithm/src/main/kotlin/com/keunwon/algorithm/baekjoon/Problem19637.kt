package com.keunwon.algorithm.baekjoon

class Problem19637 {
    fun solution(conditions: Array<Pair<String, Int>>, numbers: IntArray): Array<String> {
        conditions.sortBy { it.second }
        return numbers.map { upperBound(conditions, it) }.toTypedArray()
    }

    private fun upperBound(conditions: Array<Pair<String, Int>>, target: Int): String {
        var left = 0
        var right = conditions.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (target <= conditions[mid].second) right = mid - 1
            else left = mid + 1
        }
        return conditions[left].first
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val conditions = Array(n) {
        readLine().split(" ").let { Pair(it[0], it[1].toInt()) }
    }
    val numbers = IntArray(m) { readLine().toInt() }

    val bw = System.out.bufferedWriter()
    Problem19637().solution(conditions, numbers).forEach { bw.write("$it\n") }

    bw.flush()
    bw.close()
}
