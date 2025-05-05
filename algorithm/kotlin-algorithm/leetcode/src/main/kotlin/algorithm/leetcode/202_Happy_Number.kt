package algorithm.leetcode

class `202_Happy_Number` {
    fun isHappy(n: Int): Boolean {
        var target = n

        while (target > 9) {
            var sum = 0
            while (target > 0) {
                val a = target % 10
                sum += a * a
                target /= 10
            }
            target = sum
        }
        return target == 1
    }
}
