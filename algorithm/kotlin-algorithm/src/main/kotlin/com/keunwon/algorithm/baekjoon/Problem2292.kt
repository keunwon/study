package com.keunwon.algorithm.baekjoon

class Problem2292 {
    fun solution(n: Int): Int {
        var answer = 1
        var size = 1

        while (n > size) {
            size += (answer * 6)
            ++answer
        }
        return answer
    }
}

fun main() {
    val n = readln().toInt()
    println(Problem2292().solution(n))
}
