package com.keunwon.algorithm.programmers

/**
 * Title: 후보키
 * Level: 2
 **/
class Lessons42890 {
    private val candidates = mutableListOf<String>()

    fun solution(relation: Array<Array<String>>): Int {
        relation.indices.forEach { i ->
            combination(i + 1, BooleanArray(relation[0].size), relation)
        }
        return candidates.size
    }

    private fun combination(depth: Int, visited: BooleanArray, relation: Array<Array<String>>) {
        if (depth == 0) {
            val ids = visited.indices.mapNotNull { if (visited[it]) it else null }
            val candidateCountMap = relation.map { r -> ids.joinToString("") { r[it] } }
                .groupingBy { it }
                .eachCount()

            if (candidateCountMap.any { it.value > 1 }) return

            val idsToString = ids.joinToString("")
            candidates.onEach { candi ->
                val count = idsToString.count { candi.contains(it) }
                if (candi.length == count) return
            }.add(idsToString)

            return
        }

        relation[0].indices.forEach { i ->
            if (!visited[i]) {
                visited[i] = true
                combination(depth - 1, visited, relation)
                visited[i] = false
            }
        }
    }
}
