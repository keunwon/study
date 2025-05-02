package algorithm.leetcode

class `56_Merge_Intervals` {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith(compareBy({ it[0] }, { it[1] }))

        val result = mutableListOf<IntArray>()
        var start = intervals[0][0]
        var end = intervals[0][1]

        for (i in 1 until intervals.size) {
            val (a, b) = intervals[i]
            if (end < a) {
                result.add(intArrayOf(start, end))
                start = a
                end = b
            } else {
                end = end.coerceAtLeast(b)
            }
        }
        result.add(intArrayOf(start, end))
        return result.toTypedArray()
    }
}

fun main() {
    `56_Merge_Intervals`()
        .merge(arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18)))
        .also { list -> println(list.joinToString(" ", "[", "]") { it.joinToString(" ", "[", "]") }) }
    // expect: [[1,6],[8,10],[15,18]]

    `56_Merge_Intervals`()
        .merge(arrayOf(intArrayOf(1, 4), intArrayOf(4, 5)))
        .also { list -> println(list.joinToString(" ", "[", "]") { it.joinToString(" ", "[", "]") }) }
    // expect: [[1,5]]
}
