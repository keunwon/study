package com.keunwon.algorithm.againresolve

class AProblem3980 {
    private lateinit var map: Array<IntArray>
    private var answer = 0L

    fun solution(map: Array<IntArray>): Long {
        this.map = map

        dfs(0, 0L, BooleanArray(map.size))
        return answer
    }

    private fun dfs(depth: Int, sum: Long, visited: BooleanArray) {
        if (depth == map.size) {
            answer = answer.coerceAtLeast(sum)
            return
        }

        for ((i, num) in map[depth].withIndex()) {
            if (visited[i] || num == 0) continue

            visited[i] = true
            dfs(depth + 1, sum + num, visited)
            visited[i] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val map = Array(11) {
            readLine().split(" ")
                .map { it.toInt() }
                .toIntArray()
        }

        AProblem3980().solution(map).also {
            bw.write("$it")
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    close()
}
