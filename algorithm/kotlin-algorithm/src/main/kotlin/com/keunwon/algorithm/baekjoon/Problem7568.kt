package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 덩치
 * 난이도: 실버-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/7568">덩치 (실버-5)</a>
 **/
class Problem7568 {
    fun solution(peoples: Array<IntArray>): IntArray {
        val result = IntArray(peoples.size)

        for (i in peoples.indices) {
            var rank = 1

            for (j in peoples.indices) {
                if (peoples[i][0] < peoples[j][0] && peoples[i][1] < peoples[j][1]) {
                    ++rank
                }
            }
            result[i] = rank
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val peoples = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(2) { arr[it].toInt() }
    }

    Problem7568().solution(peoples).also { println(it.joinToString(" ")) }
}
