package algorithm.leetcode

class `1207_Unique_Number_of_Occurrences` {
    fun uniqueOccurrences(arr: IntArray): Boolean {
        val map = arr.toList().groupingBy { it }.eachCount()
        return map.size == map.values.toSet().size
    }
}
