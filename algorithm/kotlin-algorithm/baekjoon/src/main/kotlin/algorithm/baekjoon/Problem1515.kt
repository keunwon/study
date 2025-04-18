package algorithm.baekjoon

/**
 * <p>
 * 이름: 수 이어 쓰기
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1515">수 이어 쓰기 (실버-3)</a>
 **/
class Problem1515 {
    fun solution(str: String): Int {
        var cur = 0
        var idx = 0

        while (idx < str.length) {
            ++cur

            for (c in cur.toString()) {
                if (c == str[idx]) ++idx
                if (idx == str.length) break
            }
        }
        return cur
    }
}

fun main() {
    val str = readln()
    Problem1515().solution(str).also { println(it) }
}
