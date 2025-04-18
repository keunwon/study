package algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * <p>
 * 이름: 뱀
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/3190"> 뱀 (골드-4)</a>
 **/
class Problem3190 {
    fun solution(n: Int, apples: Array<IntArray>, routes: Map<Int, Char>): Int {
        val map = Array(n) { IntArray(n) }.apply {
            apples.forEach { (a, b) -> this[a - 1][b - 1] = 1 }
        }
        val snake = mutableListOf<Pair<Int, Int>>().apply { add(0 to 0) }
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        var (r, c) = 0 to 0
        var dir = 1
        var time = 0

        while (true) {
            ++time

            val (mr, mc) = moves[dir]
            val nr = r + mr
            val nc = c + mc

            if (gameOver(nr, nc, n, snake)) break

            if (map[nr][nc] == 1) {
                map[nr][nc] = 0
            } else {
                snake.removeAt(0)
            }

            snake.add(Pair(nr, nc))
            r = nr
            c = nc

            routes[time]?.let { route ->
                dir = when (route) {
                    'L' -> if (dir - 1 < 0) moves.lastIndex else dir - 1
                    'D' -> (dir + 1) % moves.size
                    else -> dir
                }
            }
        }

        return time
    }

    private fun gameOver(r: Int, c: Int, n: Int, snake: List<Pair<Int, Int>>): Boolean {
        if (r !in 0 until n || c !in 0 until n) return true
        return snake.any { r == it.first && c == it.second }
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val k = readLine().toInt()
    val apples = Array(k) {
        val st = StringTokenizer(readLine())
        IntArray(2) { st.nextToken().toInt() }
    }

    val l = readLine().toInt()
    val routes = HashMap<Int, Char>(l).apply {
        repeat(l) {
            val arr = readLine().split(" ")
            this[arr[0].toInt()] = arr[1][0]
        }
    }

    println(Problem3190().solution(n, apples, routes))
}
