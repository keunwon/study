package algorithm.leetcode

class `1281_Subtract_the_Product_and_Sum_of_Digits_of_an_Integer` {
    fun subtractProductAndSum(n: Int): Int {
        var multiplication = 1
        var sum = 0
        var target = n

        while (target > 0) {
            val mod = target % 10
            multiplication *= mod
            sum += mod
            target /= 10
        }
        return multiplication - sum
    }
}
