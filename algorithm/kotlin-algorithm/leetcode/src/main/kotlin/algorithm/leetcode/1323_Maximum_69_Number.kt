package algorithm.leetcode

class `1323_Maximum_69_Number` {
    fun maximum69Number(num: Int): Int {
        val arr = "$num".toCharArray()
        val index = arr.indexOf('6')

        if (index != -1) arr[index] = '9'
        return arr.concatToString().toInt()
    }
}
