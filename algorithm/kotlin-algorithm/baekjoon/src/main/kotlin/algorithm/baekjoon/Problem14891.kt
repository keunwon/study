package algorithm.baekjoon

import kotlin.math.pow

/**
 * <p>
 * 이름: 톱니바퀴
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14891">톱니바퀴 (골드-5)</a>
 **/
class Problem14891 {
    private lateinit var cogwheels: Array<IntArray>

    fun solution(cogwheels: Array<IntArray>, rotates: Array<IntArray>): Int {
        this.cogwheels = cogwheels
        rotates.forEach { (id, dir) -> gear(id - 1, dir) }
        return cogwheels.withIndex().sumOf { (index, wheel) -> 2.0.pow(index).toInt() * wheel[0] }
    }

    private fun gear(index: Int, dir: Int) {
        left(index - 1, -dir)
        right(index + 1, -dir)
        rotate(cogwheels[index], dir)
    }

    private fun left(index: Int, dir: Int) {
        if (index in cogwheels.indices && cogwheels[index][2] != cogwheels[index + 1][6]) {
            left(index - 1, -dir)
            rotate(cogwheels[index], dir)
        }
    }

    private fun right(index: Int, dir: Int) {
        if (index in cogwheels.indices && cogwheels[index - 1][2] != cogwheels[index][6]) {
            right(index + 1, -dir)
            rotate(cogwheels[index], dir)
        }
    }

    private fun rotate(wheel: IntArray, dir: Int) {
        if (dir == 1) {
            val tmp = wheel.last()
            for (i in wheel.lastIndex downTo 1) {
                wheel[i] = wheel[i - 1]
            }
            wheel[0] = tmp
        } else if (dir == -1) {
            val tmp = wheel[0]
            for (i in 0 until wheel.lastIndex) {
                wheel[i] = wheel[i + 1]
            }
            wheel[wheel.lastIndex] = tmp
        }
    }
}

fun main() {
    val cogwheels = Array(4) {
        val arr = readln().toCharArray()
        IntArray(8) { arr[it] - '0' }
    }
    val k = readln().toInt()
    val rotates = Array(k) {
        val arr = readln().split(" ")
        IntArray(2) { arr[it].toInt() }
    }

    Problem14891().solution(cogwheels, rotates).also(::println)
}
