class Solution {
    fun isHappy(n: Int): Boolean {
        val set = HashSet<Int>()
        var cur = n

        while (!set.contains(cur)) {
            set.add(cur)

            var next = 0
            while (cur > 0) {
                val pop = cur % 10
                next += pop * pop
                cur /= 10
            }

            if (next == 1) return true
            cur = next
        }
        return false
    }
}