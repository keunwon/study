package algorithm.baekjoon

/**
 * <p>
 * 이름: 블로그
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/21921">블로그 (실버-3)</a>
 **/
class Problem21921 {
    fun solution(x: Int, days: IntArray): String {
        val prefixSum = IntArray(days.size + 1).apply {
            days.forEachIndexed { index, day -> this[index + 1] = this[index] + day }
        }
        var max = Int.MIN_VALUE
        var count = 1

        for (i in 0 until prefixSum.size - x) {
            val tmp = prefixSum[i + x] - prefixSum[i]

            if (max == tmp) {
                ++count
            } else if (max < tmp) {
                max = tmp
                count = 1
            }
        }
        return if (max <= 0) "SAD" else "$max\n$count"
    }
}

fun main() {
    val (n, x) = readln().split(" ").map { it.toInt() }
    val days = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }

    Problem21921().solution(x, days).also { println(it) }
}
