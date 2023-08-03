package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 컨베이어 벨트 위의 로봇
 * Level: 골드-5
 **/
class Problem20055 {
    private lateinit var belt: IntArray
    private lateinit var robot: BooleanArray

    fun solution(n: Int, k: Int, belt: IntArray): Int {
        this.belt = belt
        this.robot = BooleanArray(n)
        var answer = 0

        while (true) {
            answer++
            rotate()
            move()
            if (gameOver(k)) break
        }
        return answer
    }

    private fun gameOver(k: Int): Boolean {
        return k <= belt.count { it == 0 }
    }

    private fun move() {
        if (robot.last()) {
            robot[robot.lastIndex] = false
        }

        for (i in robot.lastIndex downTo 1) {
            if (belt[i] >= 1 && !robot[i] && robot[i - 1]) {
                robot[i] = true
                robot[i - 1] = false
                belt[i]--
            }
        }

        if (belt[0] >= 1) {
            robot[0] = true
            belt[0]--
        }
    }

    private fun rotate() {
        val last = belt.last()

        for (i in belt.lastIndex downTo 1) {
            belt[i] = belt[i - 1]
        }
        belt[0] = last

        for (i in robot.lastIndex downTo 1) {
            robot[i] = robot[i - 1]
        }
        robot[0] = false
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val st = StringTokenizer(readLine())
    val belt = IntArray(n * 2) { st.nextToken().toInt() }

    Problem20055().solution(n, k, belt).also(::println)
}
