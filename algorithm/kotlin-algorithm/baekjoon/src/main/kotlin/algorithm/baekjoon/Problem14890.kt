package algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * <p>
 * 이름: 경상로
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14890">경상로 (골드-3)</a>
 **/
class Problem14890 {
    fun solution(l: Int, board: Array<IntArray>): Int {
        var result = 0

        for (i in board.indices) {
            if (check(board[i], l)) ++result

            val arr = IntArray(board.size) { board[it][i] }
            if (check(arr, l)) ++result
        }
        return result
    }

    private fun check(arr: IntArray, l: Int): Boolean {
        val visited = BooleanArray(arr.size)

        for (i in 0 until arr.lastIndex) {
            when (val diff = arr[i] - arr[i + 1]) {
                1 -> {
                    for (j in 1..l) {
                        if (i + j < arr.size && !visited[i + j] && arr[i] - arr[i + j] == diff) {
                            visited[i + j] = true
                        } else {
                            return false
                        }
                    }
                }

                -1 -> {
                    for (j in 0 until l) {
                        if (i - j >= 0 && !visited[i - j] && arr[i] == arr[i - j]) {
                            visited[i - j] = true
                        } else {
                            return false
                        }
                    }
                }

                0 -> continue

                else -> return false
            }
        }
        return true
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, l) = readLine().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }

    println(Problem14890().solution(l, board))
}
