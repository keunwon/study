package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 쿠키의 신체 측정
 * 난이도: 실버-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20125">쿠키의 신체 측정 (실버-4)</a>
 **/
class Problem20125 {
    private val moves = arrayOf(0 to 1, 1 to 0, 0 to -1)

    fun solution(board: Array<String>): Array<IntArray> {
        val heart = IntArray(2)

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == '*') {
                    var flag = true

                    for ((mr, mc) in moves) {
                        val nr = i + mr
                        val nc = j + mc

                        if (nr !in board.indices || nc !in board[0].indices || board[nr][nc] != '*') {
                            flag = false
                            break
                        }
                    }

                    if (flag) {
                        heart[0] = i
                        heart[1] = j
                        break
                    }
                }
            }
        }

        val leftArm = distance(2, Pair(heart[0], heart[1] - 1), board)
        val rightArm = distance(0, Pair(heart[0], heart[1] + 1), board)
        val waist = distance(1, Pair(heart[0] + 1, heart[1]), board)
        val leftLeg = distance(1, Pair(heart[0] + waist + 1, heart[1] - 1), board)
        val rightLeg = distance(1, Pair(heart[0] + waist + 1, heart[1] + 1), board)

        return arrayOf(
            IntArray(2) { heart[it] + 1 },
            intArrayOf(leftArm, rightArm, waist, leftLeg, rightLeg),
        )
    }

    private fun distance(dir: Int, start: Pair<Int, Int>, board: Array<String>): Int {
        val (mr, mc) = moves[dir]
        return generateSequence(start) { (r, c) -> Pair(r + mr, c + mc) }
            .takeWhile { (r, c) -> r in board.indices && c in board[0].indices && board[r][c] == '*' }
            .count()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val board = Array(n) { br.readLine() }
    Problem20125().solution(board).forEach {
        bw.write(it.joinToString(" "))
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
