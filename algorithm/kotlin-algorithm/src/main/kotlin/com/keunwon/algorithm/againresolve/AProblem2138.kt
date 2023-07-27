package com.keunwon.algorithm.againresolve

class AProblem2138 {
    private var answer = Int.MAX_VALUE

    fun solution(init: String, dest: String): Int {
        val createArr = { str: String -> str.map { it == '1' }.toBooleanArray() }
        val srcArr1 = createArr(init)
        val srcArr2 = createArr(init).apply { switching(0, this) }
        val destArr = createArr(dest)

        dfs(1, 0, srcArr1, destArr)
        dfs(1, 1, srcArr2, destArr)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(index: Int, count: Int, src: BooleanArray, dest: BooleanArray) {
        if (index == src.size) {
            if (src[index - 1] == dest[index - 1]) {
                answer = answer.coerceAtMost(count)
            }
            return
        }

        if (src[index - 1] == dest[index - 1]) {
            dfs(index + 1, count, src, dest)
            return
        }
        switching(index, src)
        dfs(index + 1, count + 1, src, dest)
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

    AProblem2138().solution(init, dest).also { println(it) }
}
