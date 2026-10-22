class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val res = mutableListOf<IntArray>()
        var idx = 0

        while (idx < intervals.size && intervals[idx][1] < newInterval[0]) {
            res.add(intervals[idx])
            ++idx
        }

        var (s, e) = newInterval
        while (idx < intervals.size && intervals[idx][0] <= e) {
            s = minOf(s, intervals[idx][0])
            e = maxOf(e, intervals[idx][1])
            ++idx
        }
        res.add(intArrayOf(s, e))

        while (idx < intervals.size) {
            res.add(intervals[idx])
            ++idx
        }
        return res.toTypedArray()
    }
}