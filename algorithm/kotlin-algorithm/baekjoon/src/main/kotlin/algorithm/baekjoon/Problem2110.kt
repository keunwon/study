package algorithm.baekjoon

/**
 * <p>
 * 이름: 공유기 설치
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2110">공유기 설치 (골드-4)</a>
 **/
// todo
class Problem2110 {
    fun solution(c: Int, homes: IntArray): Int {
        homes.sort()
        return 0
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, c) = br.readLine().split(" ").map { it.toInt() }
    val homes = IntArray(n) { br.readLine().toInt() }

    Problem2110().solution(c, homes).also { println(it) }
}
