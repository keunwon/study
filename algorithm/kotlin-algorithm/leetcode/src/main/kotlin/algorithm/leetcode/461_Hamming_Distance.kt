package algorithm.leetcode

class `461_Hamming_Distance` {
    fun hammingDistance(x: Int, y: Int): Int {
        var n = x xor y
        var result = 0

        while (n > 0) {
            result += n and 1
            n = n shr 1
        }
        return result
    }
}
