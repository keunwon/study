package algorithm.leetcode

class `1346_Check_If_N_and_Its_Double_Exist` {
    fun checkIfExist(arr: IntArray): Boolean {
        val set = HashSet<Int>(arr.size)
        var zeroCount = 0

        for (n in arr) {
            if (n == 0) ++zeroCount else set.add(n)
        }
        return zeroCount > 1 || set.any { set.contains(it * 2) }
    }
}
