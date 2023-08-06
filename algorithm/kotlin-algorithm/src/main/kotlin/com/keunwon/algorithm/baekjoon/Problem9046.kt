package com.keunwon.algorithm.baekjoon

/**
 * Title: 복호화
 * Level: 브론즈-2
 **/
class Problem9046 {
    fun solution(arr: Array<String>): Array<Char> {
        return arr.map { str ->
            val countMap = str.replace(" ", "")
                .groupingBy { it }
                .eachCount()
            val max = countMap.maxByOrNull { it.value }!!

            if (countMap.count { it.value == max.value } == 1) max.key
            else '?'
        }.toTypedArray()
    }
}

fun main() {
    val t = readLine()!!.toInt()
    val arr = Array(t) { readLine()!! }

    Problem9046().solution(arr).also {
        println(it.joinToString(System.lineSeparator()))
    }
}
