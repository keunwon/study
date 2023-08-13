package com.keunwon.algorithm.againresolve

/**
 * Title: 애너그램
 * Level: 골드-5
 **/
class AProblem6443 {
    private val answer = mutableSetOf<String>()

    fun solution(word: String): List<String> {
        dfs(0, "", word, BooleanArray(word.length))
        return answer.sorted().distinct()
    }

    private fun dfs(depth: Int, cur: String, original: String, visited: BooleanArray) {
        if (depth == original.length) {
            answer.add(cur)
            return
        }

        for ((i, c) in original.withIndex()) {
            if (visited[i]) continue

            visited[i] = true
            dfs(depth + 1, "$cur$c", original, visited)
            visited[i] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val word = readLine()

        AProblem6443().solution(word).forEach {
            bw.write("$it")
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    close()
}
