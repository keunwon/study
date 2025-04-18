package algorithm.programmers

import kotlin.math.pow

class Lesson150367 {
    fun solution(numbers: LongArray): IntArray {
        return IntArray(numbers.size) {
            val binary = generateBinary(numbers[it])
            if (check(binary)) 1 else 0
        }
    }

    private fun generateBinary(number: Long): String {
        val binary = number.toString(2)
        var nodeSize = 0
        var n = 0

        while (nodeSize < binary.length) {
            nodeSize += 2.0.pow(n).toInt()
            ++n
        }
        return "0".repeat(nodeSize - binary.length) + binary
    }

    private fun check(binary: String): Boolean {
        if (binary.length == 1) return true

        val mid = binary.length / 2
        return if (binary[mid] == '0') {
            binary.all { it == '0' }
        } else {
            check(binary.substring(0, mid)) && check(binary.substring(mid + 1))
        }
    }
}
