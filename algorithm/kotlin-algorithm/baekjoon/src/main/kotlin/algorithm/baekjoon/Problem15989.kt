package algorithm.baekjoon

/**
 * <p>
 * 이름: 1, 2, 3 더하기 4
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/15989">1, 2, 3 더하기 4 (골드-5)</a>
 **/
// todo
class Problem15989 {
    fun solution(numbers: IntArray): IntArray {
        return intArrayOf()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val numbers = IntArray(n) { br.readLine().toInt() }

    Problem15989().solution(numbers).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
