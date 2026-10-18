class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var map = HashMap<Int, Int>()

        for (i in numbers.indices) {
            var n = numbers[i]
            var m = target - n

            if (map.contains(m)) {
                return intArrayOf(map.getValue(m) + 1, i + 1)
            }
            map[n] = i
        }
        return intArrayOf(-1)
    }
}