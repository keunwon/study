class Solution {
    fun hammingWeight(n: Int): Int {
        var count = 0
        for (i in  0 until 32) {
            if (n and (1 shl i) > 0) ++count
        }
        return count
    }
}