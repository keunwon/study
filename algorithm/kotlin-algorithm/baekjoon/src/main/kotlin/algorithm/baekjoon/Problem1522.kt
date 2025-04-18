package algorithm.baekjoon

/**
 * <p>
 * 이름: 문자열 교환
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1522">문자열 교환 (실버-1)</a>
 **/
class Problem1522 {
    fun solution(str: String): Int {
        val aCount = str.count { it == 'a' }
        var result = str.length

        for (i in str.indices) {
            var bCount = 0
            for (j in i until i + aCount) {
                if (str[j % str.length] == 'b') ++bCount
            }
            result = result.coerceAtMost(bCount)
        }
        return result
    }
}

fun main() {
    val str = readln()
    Problem1522().solution(str).also { println(it) }
}
