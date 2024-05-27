package com.keunwon.algorithm.baekjoon

class Problem20310 {
    fun solution(s: String): String {
        var oneCount = s.count { it == '1' } / 2
        var zeroCount = s.count { it == '0' } / 2
        val arr = s.toCharArray()

        var index = 0
        while (index < arr.size && 0 < oneCount) {
            if (arr[index] == '1') {
                arr[index] = ' '
                --oneCount
            }
            ++index
        }

        index = arr.lastIndex
        while (0 <= index && 0 < zeroCount) {
            if (arr[index] == '0') {
                arr[index] = ' '
                --zeroCount
            }
            --index
        }
        return arr.filter { it != ' ' }.joinToString("")
    }
}

fun main() {
    val s = readln()
    println(Problem20310().solution(s))
}
