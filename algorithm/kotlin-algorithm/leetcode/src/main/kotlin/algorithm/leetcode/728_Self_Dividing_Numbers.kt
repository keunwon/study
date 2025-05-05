package algorithm.leetcode

class `728_Self_Dividing_Numbers` {
    fun selfDividingNumbers(left: Int, right: Int): List<Int> {
        return (left..right).filter { n ->
            var target = n
            while (target > 0) {
                val mod = target % 10
                if (mod == 0 || n % mod != 0) return@filter false
                target /= 10
            }
            true
        }
    }
}
