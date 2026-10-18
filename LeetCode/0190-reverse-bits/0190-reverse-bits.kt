class Solution {
    fun reverseBits(n: Int): Int {
        var res = 0
        for (i in 0 until 32) {
            res = res shl 1
            res = res or (n shr i and 1)
        }
        return res
    }
}