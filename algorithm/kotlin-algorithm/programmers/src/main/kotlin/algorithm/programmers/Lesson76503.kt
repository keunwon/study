package algorithm.programmers

import java.util.*
import kotlin.math.absoluteValue

class Lesson76503 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var numbers: LongArray
    private lateinit var indegree: IntArray

    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        return if (a.sum() != 0) -1L
        else {
            init(a, edges)
            topological()
        }
    }

    private fun init(a: IntArray, edges: Array<IntArray>) {
        this.graph = Array(a.size) { mutableListOf() }
        this.numbers = LongArray(a.size) { a[it].toLong() }
        this.indegree = IntArray(a.size)

        edges.forEach { (u, v) ->
            graph[u].add(v)
            graph[v].add(u)
            ++indegree[u]
            ++indegree[v]
        }
    }

    private fun topological(): Long {
        var answer = 0L
        val queue = LinkedList<Int>().apply {
            indegree.forEachIndexed { index, n -> if (n == 1) offer(index) }
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            val num = numbers[cur]

            --indegree[cur]
            numbers[cur] = 0
            answer += num.absoluteValue

            for (next in graph[cur]) {
                if (indegree[next] == 0) continue

                --indegree[next]
                numbers[next] += num
                if (indegree[next] == 1) queue.offer(next)
            }
        }
        return answer
    }
}
