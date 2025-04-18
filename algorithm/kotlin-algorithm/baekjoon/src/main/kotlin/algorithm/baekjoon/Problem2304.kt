package algorithm.baekjoon

/**
 * <p>
 * 이름: 창고 다각형
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2304">창고 다각형 (실버-2)</a>
 **/
class Problem2304 {
    fun solution(positions: Array<Pair<Int, Int>>): Int {
        positions.sortBy { it.first }

        var result = 0
        var pre = positions[0]
        var index = 0

        for (i in 1 until positions.size) {
            val cur = positions[i]
            if (pre.second <= cur.second) {
                result += (cur.first - pre.first) * pre.second
                pre = cur
                index = i
            }
        }

        result += positions[index].second
        pre = positions.last()
        for (i in positions.lastIndex - 1 downTo index) {
            val cur = positions[i]
            if (pre.second <= cur.second) {
                result += (pre.first - cur.first) * pre.second
                pre = cur
            }
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val positions = Array(n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        arr[0] to arr[1]
    }
    Problem2304().solution(positions).also { println(it) }
}
