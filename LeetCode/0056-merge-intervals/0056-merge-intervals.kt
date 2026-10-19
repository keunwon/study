class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith(compareBy({ it[0] }, { it[1] }))

        val res = mutableListOf<IntArray>()
        var start = intervals[0][0]
        var end = intervals[0][1]

        for (i in 1 until intervals.size) {
            val (s, e) = intervals[i]
            if (end >= s) {
                end = maxOf(end, e)
            } else {
                res.add(intArrayOf(start, end))
                start = s
                end = e
            }
        }
        res.add(intArrayOf(start, end))

        return res.toTypedArray()
    }
}