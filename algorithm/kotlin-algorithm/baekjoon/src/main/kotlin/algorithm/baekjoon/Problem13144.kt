package algorithm.baekjoon

/**
 * <p>
 * 이름: List of Unique Numbers
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/13144">List of Unique Numbers (골드-4)</a>
 **/
class Problem13144 {
    fun solution(numbers: IntArray): Long {
        val arr = IntArray(100_001).apply { ++this[numbers[0]] }
        var left = 0
        var right = 0
        var result = 0L

        while (left < numbers.size) {
            while (right + 1 < numbers.size && arr[numbers[right + 1]] == 0) {
                ++arr[numbers[++right]]
            }

            result += right - left + 1
            --arr[numbers[left++]]
        }
        return result
    }
}

fun main() {
    val n = readln().toInt()
    val numbers = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }
    Problem13144().solution(numbers).also { println(it) }
}
