package algorithm.programmers

/**
 * <p>
 * 이름:올바른 괄호의 갯수
 * 난이도: Level-4
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12929">올바른 괄호의 갯수 (Level-4)</a>
 **/
class Lesson12929 {
    private var result = 0

    fun solution(n: Int): Int {
        dfs(n, 0, 0)
        return result
    }

    private fun dfs(n: Int, open: Int, close: Int) {
        if (open == n && close == n) {
            ++result
            return
        }

        if (n > open) dfs(n, open + 1, close)
        if (open > close) dfs(n, open, close + 1)
    }
}

fun main() {
    Lesson12929().solution(14).also { println(it) }
}
