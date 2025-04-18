package com.keunwon.algorithm.baekjoon

import java.util.Stack
import kotlin.math.abs

/**
 * <p>
 * 이름: 고층 건물
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1027">고층 건물 (골드-4)</a>
 **/
class Problem1027 {
    fun solution(heights: IntArray): Array<IntArray> {
        val result = Array(heights.size) { intArrayOf(0, 1e9.toInt()) }
        val stack = Stack<Int>()

        for (i in heights.indices) {
            while (stack.isNotEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop()
            }
            result[i][0] += stack.size
            if (stack.isNotEmpty()) result[i][1] = stack.peek()
            stack.push(i)
        }

        stack.clear()
        for (i in heights.lastIndex downTo 0) {
            while (stack.isNotEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop()
            }
            result[i][0] += stack.size
            if (stack.isNotEmpty() && abs(i - result[i][1]) > abs(i - stack.peek())) {
                result[i][1] = stack.peek()
            }
            stack.push(i)
        }

        for (i in result.indices) {
            if (result[i][0] == 0) result[i] = intArrayOf(0) else ++result[i][1]
        }
        return result
    }
}

fun main() {
    val bw = System.out.bufferedWriter()
    val n = readln().toInt()
    val heights = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }

    Problem1027().solution(heights).forEach {
        bw.write(it.joinToString(" "))
        bw.newLine()
    }
    bw.close()
}
