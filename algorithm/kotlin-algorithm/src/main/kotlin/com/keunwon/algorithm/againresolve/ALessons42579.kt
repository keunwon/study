package com.keunwon.algorithm.againresolve

class ALessons42579 {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices.groupBy { genres[it] }
            .toList()
            .sortedByDescending { (_, list) -> list.sumOf { plays[it] } }
            .flatMap { (_, list) -> list.sortedWith(compareBy({ -plays[it] }, { it })).take(2) }
            .toIntArray()
    }
}

fun main() {
    ALessons42579().solution(
        arrayOf("classic", "pop", "classic", "classic", "pop"),
        intArrayOf(500, 600, 150, 800, 2500)
    ).let { println(it.contentToString()) }
}
