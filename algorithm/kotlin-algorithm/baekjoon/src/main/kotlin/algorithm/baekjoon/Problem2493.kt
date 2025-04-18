package algorithm.baekjoon

import java.util.Stack

/**
 * <p>
 * 이름: 탑
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2493">탑 (골드-5)</a>
 **/
class Problem2493 {
    fun solution(heights: IntArray): IntArray {
        val stack = Stack<Int>().apply { push(0) }
        val result = IntArray(heights.size)

        for (i in 1 until heights.size) {
            while (stack.isNotEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop()
            }

            result[i] = if (stack.isEmpty()) 0 else stack.peek() + 1
            stack.push(i)
        }
        return result
    }
}

fun main() {
    val n = readln().toInt()
    val heights = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }
    Problem2493().solution(heights).also { println(it.joinToString(" ")) }
}
