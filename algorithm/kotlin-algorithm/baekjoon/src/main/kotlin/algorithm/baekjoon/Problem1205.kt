package algorithm.baekjoon

/**
 * <p>
 * 이름: 등수 구하기
 * 난이도: 실버-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1205">등수 구하기 (실버-4)</a>
 **/
class Problem1205 {
    fun solution(point: Int, p: Int, numbers: IntArray): Int {
        if (numbers.isEmpty()) return 1
        if (numbers.size == p && numbers.last() >= point) return -1

        var rank = 1
        for (num in numbers) {
            if (point >= num) break
            ++rank
        }
        return rank
    }
}

fun main() {
    val (n, point, p) = readln().split(" ").map { it.toInt() }
    val numbers = if (n > 0) {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    } else {
        intArrayOf()
    }

    Problem1205().solution(point, p, numbers).also { println(it) }
}
