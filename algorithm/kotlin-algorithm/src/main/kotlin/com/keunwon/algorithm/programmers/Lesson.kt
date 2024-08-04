package com.keunwon.algorithm.programmers

import java.util.*
import kotlin.math.absoluteValue

class Lesson {
    private lateinit var numbers: LongArray
    private lateinit var indegree: IntArray
    private lateinit var graph: Array<MutableList<Int>>

    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        return if (a.sum() != 0) {
            -1L
        } else {
            init(a, edges)
            topological()
        }
    }

    private fun topological(): Long {
        var result = 0L
        val queue = LinkedList<Int>().apply {
            indegree.forEachIndexed { index, n -> if (n == 1) offer(index) }
        }

        while (queue.isNotEmpty()) {
            val index = queue.poll()
            val n = numbers[index]

            --indegree[index]
            numbers[index] = 0
            result += n.absoluteValue

            for (next in graph[index]) {
                if (indegree[next] == 0) continue

                --indegree[next]
                numbers[next] += n
                if (indegree[next] == 1) queue.offer(next)
            }
        }
        return result
    }

    private fun init(a: IntArray, edges: Array<IntArray>) {
        this.numbers = LongArray(a.size) { a[it].toLong() }
        this.indegree = IntArray(a.size)
        this.graph = Array(a.size) { mutableListOf() }

        edges.forEach { (u, v) ->
            graph[u].add(v)
            graph[v].add(u)
            ++indegree[u]
            ++indegree[v]
        }
    }
}

fun main() {
    val result1 = Lesson().solution(
        intArrayOf(-5, 0, 2, 1, 2),
        arrayOf(intArrayOf(0, 1), intArrayOf(3, 4), intArrayOf(2, 3), intArrayOf(0, 3))
    )
    println(result1) // 9

    val result2 = Lesson().solution(intArrayOf(0, 1, 0), arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)))
    println(result2) // -1
}
