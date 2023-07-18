package com.keunwon.algorithm.baekjoon

/**
 * Title: 타노스
 * Level: 실버-3
 **/
class Problem20310 {
    fun solution(binary: String): String {
        var oneCount = binary.count { it == '1' } / 2
        var zeroCount = binary.count { it == '0' } / 2
        val arr = binary.toCharArray()

        var cur1 = 0
        while (cur1 < binary.length && 0 < oneCount) {
            if (arr[cur1] == '1') {
                arr[cur1] = ' '
                oneCount--
            }
            cur1++
        }

        var cur2 = binary.lastIndex
        while (0 <= cur2 && 0 < zeroCount) {
            if (arr[cur2] == '0') {
                arr[cur2] = ' '
                zeroCount--
            }
            cur2--
        }
        return arr.filter { it != ' ' }.joinToString("")
    }
}

fun main() {
    val binary = readLine()!!
    Problem20310().solution(binary).also { println(it) }
}
