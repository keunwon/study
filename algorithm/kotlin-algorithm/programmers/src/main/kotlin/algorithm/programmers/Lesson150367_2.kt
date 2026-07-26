package algorithm.programmers

class Lesson150367_2 {
    fun solution(numbers: LongArray): IntArray {
        return IntArray(numbers.size) { i ->
            val x = numbers[i]
            val bitLength = 64 - x.countLeadingZeroBits()
            var length = 1

            while (bitLength > length) {
                length = (length shl 1) or 1
            }
            if (check(x, length, 0, length - 1)) 1 else 0
        }
    }

    private fun check(x: Long, length: Int, left: Int, right: Int): Boolean {
        if (left >= right) return true

        val mid = (left + right) ushr 1
        val rootBit = (x shr (length - mid - 1)) and 1L

        return if (rootBit == 0L) {
            val subLength = right - left + 1
            val mask = ((1L shl subLength) - 1L) shl (length - right - 1)
            (x and mask) == 0L
        } else {
            check(x, length, left, mid - 1) && check(x, length, mid + 1, right)
        }
    }
}
