package com.keunwon.algorithm.baekjoon

/**
 * Title: 파일 정리
 * Level: 실버-3
 **/
class Problem20291 {
    fun solution(fileNames: Array<String>): Array<Pair<String, Int>> {
        return fileNames.groupingBy { it.split(".")[1] }
            .eachCount()
            .map { it.key to it.value }
            .sortedBy { it.first }
            .toTypedArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val fileNames = Array(n) { readLine()!! }

    Problem20291().solution(fileNames).forEach { (extension, count) ->
        println("$extension $count")
    }
}
