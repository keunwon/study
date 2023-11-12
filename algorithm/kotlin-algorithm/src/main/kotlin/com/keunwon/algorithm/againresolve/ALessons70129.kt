package com.keunwon.algorithm.againresolve

class ALessons70129 {
    fun solution(s: String): IntArray {
        var countRepeat = 0
        var countZero = 0
        var binary = s

        while (binary != "1") {
            ++countRepeat
            countZero += binary.count { it == '0' }
            binary = binary.count { it == '1' }.toString(2)
        }
        return intArrayOf(countRepeat, countZero)
    }
}

fun main() {
    ALessons70129().solution("110010101001")
        .also { println(it.contentToString()) }
}
