package com.keunwon.algorithm.againresolve

class ALessons42890 {
    private val candidates = mutableListOf<String>()

    fun solution(relation: Array<Array<String>>): Int {
        for (size in 1..relation[0].size) {
            combination(size, BooleanArray(relation[0].size), relation)
        }
        return candidates.size
    }

    private fun combination(
        size: Int,
        visited: BooleanArray,
        relation: Array<Array<String>>,
    ) {
        if (size == 0) {
            val ids = visited.indices.joinToString("") { if (visited[it]) "$it" else "" }
            val isCandidateKeys = relation
                .map { r -> ids.map { r[it.digitToInt()] }.joinToString("") }
                .groupingBy { it }
                .eachCount()
                .all { it.value == 1 }

            if (!isCandidateKeys) return

            val isUnique = candidates.all { c -> c.length != ids.count(c::contains) }
            if (isUnique) candidates.add(ids)
            return
        }

        for (i in visited.indices) {
            if (!visited[i]) {
                visited[i] = true
                combination(size - 1, visited, relation)
                visited[i] = false
            }
        }
    }
}

fun main() {
    ALessons42890().solution(
        arrayOf(
            arrayOf("100", "ryan", "music", "2"),
            arrayOf("200", "apeach", "math", "2"),
            arrayOf("300", "tube", "computer", "3"),
            arrayOf("400", "con", "computer", "4"),
            arrayOf("500", "muzi", "music", "3"),
            arrayOf("600", "apeach", "music", "2")
        )
    ).also(::println)

    ALessons42890().solution(
        arrayOf(
            arrayOf("1", "2", "3"),
            arrayOf("1", "2", "3"),
        )
    ).also(::println)
}
