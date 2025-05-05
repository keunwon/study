package algorithm.leetcode

class `258_Add_Digits` {
    fun addDigits(num: Int): Int {
        var target = num
        while (target > 9) {
            var sum = 0
            while (target > 0) {
                sum += target % 10
                target /= 10
            }
            target = sum
        }
        return target
    }
}
