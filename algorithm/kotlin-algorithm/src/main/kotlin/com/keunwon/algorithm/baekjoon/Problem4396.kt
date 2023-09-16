package com.keunwon.algorithm.baekjoon

/**
 * Title: 지뢰 찾기
 * Level: 실버-4
 **/
class Problem4396 {
    fun solution(mineMap: Array<CharArray>, answerMap: Array<CharArray>): Array<CharArray> {
        val answer = Array(mineMap.size) { CharArray(mineMap[0].size) { '.' } }
        var isBoom = false

        for (i in answerMap.indices) {
            for ((j, type) in answerMap[i].withIndex()) {
                if (type != 'x') continue

                if (mineMap[i][j] == '*') isBoom = true
                else answer[i][j] = "${countByMine(mineMap, Pair(i, j))}"[0]
            }
        }

        if (isBoom) {
            for (i in mineMap.indices) {
                for ((j, type) in mineMap[i].withIndex()) {
                    if (type == '*') answer[i][j] = '*'
                }
            }
        }
        return answer
    }

    private fun countByMine(mineMap: Array<CharArray>, position: Pair<Int, Int>): Int {
        val (r, c) = position
        val positions = arrayOf(
            Pair(r - 1, c - 1),
            Pair(r - 1, c),
            Pair(r - 1, c + 1),
            Pair(r, c - 1),
            Pair(r, c + 1),
            Pair(r + 1, c - 1),
            Pair(r + 1, c),
            Pair(r + 1, c + 1),
        )
        return positions.count { (nr, nc) ->
            nr in mineMap.indices && nc in mineMap[0].indices && mineMap[nr][nc] == '*'
        }
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val mineMap = Array(n) { br.readLine().toCharArray() }
        val map = Array(n) { br.readLine().toCharArray() }

        Problem4396().solution(mineMap, map).forEach {
            bw.write("${it.joinToString("")}")
            bw.newLine()
        }
        bw.flush()
    }
}
