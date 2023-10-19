package com.keunwon.algorithm.baekjoon

import com.keunwon.algorithm.baekjoon.Problem2615.Answer2615.Type
import java.util.*

/**
 * Title: 오목
 * Level: 실버-1
 **/
class Problem2615 {
    fun solution(board: Array<IntArray>): Answer2615 {
        for (i in board.indices) {
            for ((j, num) in board[i].withIndex()) {
                if (num != 0 && check(Pair(i, j), board)) {
                    return Answer2615(Type.of(num), Pair(i + 1, j + 1))
                }
            }
        }
        return Answer2615(Type.NONE)
    }

    private fun check(src: Pair<Int, Int>, board: Array<IntArray>): Boolean {
        return false
    }

    class Answer2615(val type: Type, val position: Pair<Int, Int>? = null) {
        enum class Type(val num: Int) {
            NONE(0),
            BLACK(1),
            WHITE(2);

            companion object {
                fun of(num: Int): Type = values().find { it.num == num } ?: error("$num not found")
            }
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val board = Array(19) {
        val st = StringTokenizer(readLine())
        IntArray(19) { st.nextToken().toInt() }
    }

    with(Problem2615().solution(board)) {
        bw.write(type.num)
        bw.newLine()
        position?.let { (r, c) -> bw.write("$r $c") }
    }
    bw.flush()
}
