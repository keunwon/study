package algorithm.leetcode

class `575_Distribute_Candies` {
    fun distributeCandies(candyType: IntArray): Int {
        val max = candyType.size / 2
        val set = HashSet<Int>(max)

        for (type in candyType) {
            set.add(type)
            if (set.size == max) return max
        }
        return set.size
    }
}
