package com.keunwon.algorithm.againresolve

class ALessons42890 {
    private lateinit var relation: Array<Array<String>>
    private val answer = mutableSetOf<String>()

    fun solution(relation: Array<Array<String>>): Int {
        this.relation = relation

        for (i in 1..relation[0].size) combination(0, 0, IntArray(i))
        return answer.size
    }

    private fun combination(depth: Int, startIndex: Int, picks: IntArray) {
        if (depth == picks.size) {
            if (check(picks)) answer.add(picks.joinToString(""))
            return
        }
        for (i in startIndex until relation[0].size) {
            picks[depth] = i
            combination(depth + 1, i + 1, picks)
        }
    }

    private fun check(picks: IntArray): Boolean {
        val countMap = relation
            .groupingBy { arr -> picks.joinToString("") { arr[it] } }
            .eachCount()

        val isSingleKey = countMap.all { it.value == 1 }
        val notDuplication = answer.all { s -> s.length != picks.count { "$it" in s } }
        return isSingleKey && notDuplication
    }
}

fun main() {
    val relation = arrayOf(
        arrayOf("100", "ryan", "music", "2"),
        arrayOf("200", "apeach", "math", "2"),
        arrayOf("300", "tube", "computer", "3"),
        arrayOf("400", "con", "computer", "4"),
        arrayOf("500", "muzi", "music", "3"),
        arrayOf("600", "apeach", "music", "2"),
    )
    ALessons42890().solution(relation).also { println(it) }
}
