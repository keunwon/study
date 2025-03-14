package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 줄세우기
 * 난이도: 실버-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/10431">줄세우기 (실버-5)</a>
 **/
class Problem10431 {
    fun solution(lines: Array<IntArray>): Array<Int> {
        return Array(lines.size) { index ->
            val line = lines[index]
            var count = 0

            for (i in line.indices) {
                for (j in i + 1 until line.size) {
                    if (line[i] > line[j]) ++count
                }
            }
            count
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val lines = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(20) { arr[it + 1].toInt() }
    }

    Problem10431().solution(lines).forEachIndexed { index, num ->
        bw.write("${index + 1} $num")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
