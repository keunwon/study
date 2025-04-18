package algorithm.baekjoon

/**
 * <p>
 * 이름: 하늘에서 별똥별이 빗발친다
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14658">하늘에서 별똥별이 빗발친다 (골드-3)</a>
 **/
// todo
class Problem14658 {
    fun solution(n: Int, m: Int, l: Int, shootingStarts: Array<Pair<Int, Int>>): Int {
        return 0
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m, l, k) = br.readLine().split(" ").map { it.toInt() }
    val shootingStars = Array(k) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        arr[0] to arr[1]
    }

    Problem14658().solution(n, m, l, shootingStars).also { println(it) }
    br.close()
}
