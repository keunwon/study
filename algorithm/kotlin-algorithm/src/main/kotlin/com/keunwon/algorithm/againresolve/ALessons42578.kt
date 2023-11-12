package com.keunwon.algorithm.againresolve

class ALessons42578 {
    fun solution(clothes: Array<Array<String>>): Int {
        return clothes.groupBy { it.last() }
            .map { it.value.size }
            .fold(1) { acc, n -> acc * (n + 1) } - 1
    }
}

fun main() {
    ALessons42578().solution(
        arrayOf(
            arrayOf("yellow_hat", "headgear"),
            arrayOf("blue_sunglasses", "eyewear"),
            arrayOf("green_turban", "headgear")
        )
    ).also(::println)
}
