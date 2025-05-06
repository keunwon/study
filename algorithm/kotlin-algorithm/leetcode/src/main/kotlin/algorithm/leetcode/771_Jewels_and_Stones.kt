package algorithm.leetcode

class `771_Jewels_and_Stones` {
    fun numJewelsInStones(jewels: String, stones: String): Int {
        val set = jewels.toSet()
        return stones.count { set.contains(it) }
    }
}
