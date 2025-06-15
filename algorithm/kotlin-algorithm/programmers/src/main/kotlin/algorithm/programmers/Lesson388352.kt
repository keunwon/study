package algorithm.programmers

/**
 * <p>
 * 이름:비밀 코드 해독
 * 난이도:Level-2
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/388352">비밀 코드 해독 (Level-2)</a>
 **/
class Lesson388352 {
    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        return dfs(q, ans, IntArray(5), n, 1, 0)
    }

    private fun dfs(
        q: Array<IntArray>,
        ans: IntArray,
        picks: IntArray,
        n: Int,
        sIndex: Int,
        depth: Int,
    ): Int {
        if (depth == picks.size) {
            val numbers = IntArray(q.size) { index -> q[index].count { picks.contains(it) } }
            return if (numbers.contentEquals(ans)) 1 else 0
        }

        var result = 0
        for (i in sIndex..n) {
            picks[depth] = i
            result += dfs(q, ans, picks, n, i + 1, depth + 1)
        }

        return result
    }
}
