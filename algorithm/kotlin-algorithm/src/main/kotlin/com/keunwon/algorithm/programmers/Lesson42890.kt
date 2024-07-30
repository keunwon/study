package com.keunwon.algorithm.programmers

class Lesson42890 {
    private lateinit var relation: Array<Array<String>>

    private val candidates = mutableListOf<String>()

    fun solution(relation: Array<Array<String>>): Int {
        this.relation = relation
        relation[0].indices.forEach { dfs(it + 1, BooleanArray(relation[0].size)) }
        return candidates.size
    }

    private fun dfs(size: Int, visited: BooleanArray) {
        if (size == 0) {
            val ids = visited.indices.mapNotNull { if (visited[it]) it else null }
            val countMap = relation.groupingBy { r -> ids.joinToString("") { r[it] } }.eachCount()

            if (countMap.any { it.value > 1 }) return

            candidates.forEach { candi ->
                val count = ids.count { candi.contains("$it") }
                if (count == candi.length) return
            }
            candidates.add(ids.joinToString(""))
            return
        }

        relation[0].indices.forEach { i ->
            if (!visited[i]) {
                visited[i] = true
                dfs(size - 1, visited)
                visited[i] = false
            }
        }
    }
}
