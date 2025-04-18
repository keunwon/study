package algorithm.baekjoon

/**
 * <p>
 * 이름: 햄버거 분배
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/19941">햄버거 분배 (실버-3)</a>
 **/
class Problem19941 {
    fun solution(k: Int, str: String): Int {
        val visited = BooleanArray(str.length)
        var result = 0

        for (i in str.indices) {
            if (str[i] == 'P') {
                val s = (i - k).coerceAtLeast(0)
                val e = (i + k).coerceAtMost(str.lastIndex)

                for (j in s..e) {
                    if (!visited[j] && str[j] == 'H') {
                        visited[j] = true
                        ++result
                        break
                    }
                }
            }
        }
        return result
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val str = readln()

    Problem19941().solution(k, str).also { println(it) }
}
