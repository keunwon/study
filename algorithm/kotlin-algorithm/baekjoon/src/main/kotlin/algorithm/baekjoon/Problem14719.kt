package algorithm.baekjoon

/**
 * <p>
 * 이름: 빗물
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14719">빗물 (골드-5)</a>
 **/
class Problem14719 {
    fun solution(h: Int, heights: IntArray): Int {
        var result = 0

        for (i in 1 until heights.lastIndex) {
            var left = 0
            var right = 0

            for (j in 0 until i) {
                left = left.coerceAtLeast(heights[j])
            }

            for (j in i + 1 until heights.size) {
                right = right.coerceAtLeast(heights[j])
            }

            if (left > heights[i] && right > heights[i]) {
                result += left.coerceAtMost(right) - heights[i]
            }
        }
        return result
    }
}

fun main() {
    val (h, w) = readln().split(" ").map { it.toInt() }
    val heights = readln().split(" ").map { it.toInt() }.toIntArray()
    Problem14719().solution(h, heights).also { println(it) }
}
