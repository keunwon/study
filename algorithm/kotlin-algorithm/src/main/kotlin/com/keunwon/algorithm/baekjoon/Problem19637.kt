package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: IF문 좀 대신 써줘
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/19637">IF문 좀 대신 써줘 (실버-2)</a>
 **/
class Problem19637 {
    fun solution(conditions: Array<Pair<String, Int>>, powers: IntArray): Array<String> {
        return Array(powers.size) { index ->
            val power = powers[index]
            var left = 0
            var right = conditions.lastIndex

            while (left <= right) {
                val mid = (left + right) / 2
                if (power <= conditions[mid].second) right = mid - 1 else left = mid + 1
            }
            conditions[left].first
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val conditions = Array(n) {
        val (name, maxPower) = br.readLine().split(" ")
        name to maxPower.toInt()
    }
    val powers = IntArray(m) { br.readLine().toInt() }

    Problem19637().solution(conditions, powers).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
