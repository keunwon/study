class Solution {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val idMap = HashMap<String, Int>()
        val freqMap = HashMap<String, MutableSet<String>>()

        for ((i, id) in id_list.withIndex()) {
            idMap[id] = i
            freqMap[id] = mutableSetOf()
        }

        for (r in report) {
            val (a, b) = r.split(" ")
            freqMap.getValue(b).add(a)
        }

        val res = IntArray(id_list.size)

        for ((_, set) in freqMap) {
            if (set.size < k) continue

            for (id in set) {
                ++res[idMap.getValue(id)]
            }
        }
        return res
    }
}