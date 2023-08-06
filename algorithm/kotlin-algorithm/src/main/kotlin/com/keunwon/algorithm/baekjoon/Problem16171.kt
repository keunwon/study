package com.keunwon.algorithm.baekjoon

/**
 * Title: 나는 친구가 적다 (Small)
 * Level: 브론즈-2
 **/
class Problem16171 {
    fun solution(str1: String, str2: String): Int {
        val target = str1.replace("\\d".toRegex(), "")
        return if (target.contains(str2)) 1 else 0
    }
}

fun main() {
    Problem16171().solution(
        readLine()!!,
        readLine()!!,
    ).also(::println)
}
