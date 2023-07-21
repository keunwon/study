package com.keunwon.algorithm.baekjoon

/**
 * Title: 창고 다각형
 * Level: 실버-2
 **/
class Problem2304 {
    fun solution(arr: Array<Pair<Int, Int>>): Int {
        val nodes = arr.map { (x, y) -> Node(x, y) }.sortedBy { it.x }
        var area = 0

        var prev = nodes.first()
        var index = 0
        for (i in 1 until nodes.size) {
            val cur = nodes[i]
            if (prev.y <= cur.y) {
                area += (cur.x - prev.x) * prev.y
                prev = cur
                index = i
            }
        }

        prev = nodes.last()
        for (i in 1 until nodes.size - index) {
            val cur = nodes[nodes.lastIndex - i]
            if (prev.y <= cur.y) {
                area += (prev.x - cur.x) * prev.y
                prev = cur
            }
        }
        area += nodes[index].y
        return area
    }

    private data class Node(val x: Int, val y: Int)
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    Problem2304().solution(arr).also { println(it) }
}
