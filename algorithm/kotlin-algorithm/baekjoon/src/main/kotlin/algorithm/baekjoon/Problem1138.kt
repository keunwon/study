package algorithm.baekjoon

/**
 * <p>
 * 이름: 한 줄로 서기
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1138">한 줄로 서기 (실버-2)</a>
 **/
class Problem1138 {
    fun solution(numbers: IntArray): IntArray {
        val result = mutableListOf<Int>()
        for (i in numbers.size downTo 1) {
            result.add(numbers[i - 1], i)
        }
        return result.toIntArray()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val numbers = run {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }
    Problem1138().solution(numbers).also { println(it.joinToString(" ")) }
}
