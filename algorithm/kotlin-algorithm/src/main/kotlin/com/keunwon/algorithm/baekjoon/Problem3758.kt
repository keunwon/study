package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: KCPC
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/3758">KCPC (실버-2)</a>
 **/
class Problem3758 {
    fun solution(n: Int, k: Int, id: Int, logs: Array<IntArray>): Int {
        val map = (1..n).associateWith { Node(0, IntArray(k + 1), -1) }
        logs.forEachIndexed { index, (teamId, no, point) ->
            map[teamId]?.apply {
                ++summitCount
                points[no] = points[no].coerceAtLeast(point)
                modify = index
            }
        }
        return map.entries
            .sortedWith(compareBy({ -it.value.points.sum() }, { it.value.summitCount }, { it.value.modify }))
            .indexOfFirst { it.key == id } + 1
    }

    private class Node(
        var summitCount: Int,
        val points: IntArray,
        var modify: Int,
    )
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()

    repeat(t) {
        val (n, k, id, m) = br.readLine().split(" ").map { it.toInt() }
        val logs = Array(m) {
            val arr = br.readLine().split(" ").map { it.toInt() }
            IntArray(3) { arr[it] }
        }

        Problem3758().solution(n, k, id, logs).also {
            bw.write("$it")
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    br.close()
}
