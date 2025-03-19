package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 타노스
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20310">타노스 (실버-3)</a>
 **/
class Problem20310 {
    fun solution(str: String): String {
        val arr = str.toCharArray()
        var oneCount = arr.count { it == '1' } / 2
        var zeroCount = arr.count { it == '0' } / 2

        var index = 0
        while (index < arr.size && 0 < oneCount) {
            if (arr[index] == '1') {
                arr[index] = ' '
                --oneCount
            }
            ++index
        }

        index = str.lastIndex
        while (index >= 0 && zeroCount > 0) {
            if (arr[index] == '0') {
                arr[index] = ' '
                --zeroCount
            }
            --index
        }

        return buildString(str.length / 2) {
            arr.forEach { if (!it.isWhitespace()) append(it) }
        }
    }
}

fun main() {
    val str = readln()
    Problem20310().solution(str).also { println(it) }
}
