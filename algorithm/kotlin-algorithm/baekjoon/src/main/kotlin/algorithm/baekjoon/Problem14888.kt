package algorithm.baekjoon

/**
 * <p>
 * 이름: 연산자 끼워넣기
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14888">연산자 끼워넣기 (실버-1)</a>
 **/
class Problem14888 {
    private lateinit var numbers: IntArray
    private lateinit var operators: IntArray

    private val result = intArrayOf(Int.MIN_VALUE, Int.MAX_VALUE)

    fun solution(numbers: IntArray, operators: IntArray): IntArray {
        this.numbers = numbers
        this.operators = operators

        dfs(1, numbers[0])
        return result
    }

    private fun dfs(index: Int, sum: Int) {
        if (index == numbers.size) {
            result[0] = result[0].coerceAtLeast(sum)
            result[1] = result[1].coerceAtMost(sum)
            return
        }

        for (i in operators.indices) {
            if (operators[i] > 0) {
                --operators[i]

                val calcNum = when (i) {
                    0 -> sum + numbers[index]
                    1 -> sum - numbers[index]
                    2 -> sum * numbers[index]
                    3 -> sum / numbers[index]
                    else -> 0
                }
                dfs(index + 1, calcNum)

                ++operators[i]
            }
        }
    }
}

fun main() {
    val n = readln().toInt()
    val numbers = run {
        val arr = readln().split(" ")
        IntArray(n) { arr[it].toInt() }
    }
    val operators = run {
        val arr = readln().split(" ")
        IntArray(4) { arr[it].toInt() }
    }

    Problem14888().solution(numbers, operators).forEach { println(it) }
}
