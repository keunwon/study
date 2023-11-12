package com.keunwon.algorithm.againresolve

import kotlin.math.max

class ALessons87390 {
    fun solution(n: Int, left: Long, right: Long): IntArray =
        (left..right).map { max(it / n, it % n).toInt() + 1 }.toIntArray()
}

fun main() {
    ALessons87390().solution(3, 2, 5).also { println(it.contentToString()) }
}
