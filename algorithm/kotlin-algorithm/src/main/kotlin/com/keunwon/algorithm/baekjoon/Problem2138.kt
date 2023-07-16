package com.keunwon.algorithm.baekjoon

/**
 * Title: 전구와 스위치
 * Level: 골드-5
 **/
class Problem2138 {
    private var answer = Int.MAX_VALUE

    fun solution(init: String, dest: String): Int {
        val createArr = { str: String -> str.map { it == '1' }.toBooleanArray() }
        val initArr1 = createArr(init)
        val initArr2 = createArr(init).apply { switching(0, this) }
        val destArr = createArr(dest)

        dfs(1, 0, initArr1, destArr)
        dfs(1, 1, initArr2, destArr)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(index: Int, count: Int, arr: BooleanArray, destArr: BooleanArray) {
        if (index == arr.size) {
            if (arr[index - 1] == destArr[index - 1]) {
                answer = answer.coerceAtMost(count)
            }
            return
        }

        if (arr[index - 1] == destArr[index - 1]) {
            dfs(index + 1, count, arr, destArr)
            return
        }
        switching(index, arr)
        dfs(index + 1, count + 1, arr, destArr)
    }

    private fun switching(index: Int, arr: BooleanArray) {
        for (i in index - 1..index + 1) {
            if (i in arr.indices) arr[i] = !arr[i]
        }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val init = readLine()!!
    val dest = readLine()!!

    Problem2138().solution(init, dest).also { println(it) }
}
