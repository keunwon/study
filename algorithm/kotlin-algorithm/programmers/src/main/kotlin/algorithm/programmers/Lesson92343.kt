package algorithm.programmers

import kotlin.math.max

class Lesson92343 {
    private lateinit var info: IntArray
    private lateinit var graph: Array<MutableList<Int>>

    private var answer = 0

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        this.info = info
        this.graph = Array(info.size) { mutableListOf<Int>() }.apply {
            edges.forEach { (a, b) -> this[a].add(b) }
        }
        dfs(0, 0, 0, listOf())
        return answer
    }

    private fun dfs(sheepCount: Int, wolfCount: Int, cur: Int, nextNodes: List<Int>) {
        var sCount = sheepCount
        var wCount = wolfCount

        if (info[cur] == 0) ++sCount else ++wCount
        if (sCount <= wCount) return

        answer = max(answer, sCount)

        val list = graph[cur].toMutableList().apply {
            addAll(nextNodes)
            remove(cur)
        }
        list.forEach { n -> dfs(sCount, wCount, n, list) }
    }
}
