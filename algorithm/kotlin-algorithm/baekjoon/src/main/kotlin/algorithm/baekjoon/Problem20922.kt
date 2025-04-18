package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: 겹치는 건 싫어
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20922">겹치는 건 싫어 (실버-1)</a>
 **/
class Problem20922 {
    fun solution(k: Int, numbers: IntArray): Int {
        val countMap = mutableMapOf<Int, Int>()
        val q = LinkedList<Int>()
        var max = 0

        for (num in numbers) {
            while (countMap.getOrDefault(num, 0) >= k) {
                val tmpKey = q.poll()
                countMap[tmpKey] = countMap.getOrDefault(tmpKey, 0) - 1
            }

            q.offer(num)
            countMap[num] = countMap.getOrDefault(num, 0) + 1
            max = max.coerceAtLeast(q.size)
        }
        return max
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val numbers = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }

    Problem20922().solution(k, numbers).also { println(it) }
}
