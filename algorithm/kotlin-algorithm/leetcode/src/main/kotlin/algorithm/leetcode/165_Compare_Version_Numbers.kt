package algorithm.leetcode

class `165_Compare_Version_Numbers` {
    fun compareVersion(version1: String, version2: String): Int {
        val arr1 = version1.split(".").map { it.toInt() }
        val arr2 = version2.split(".").map { it.toInt() }
        val maxSize = arr1.size.coerceAtLeast(arr2.size)

        for (i in 0 until maxSize) {
            val n1 = arr1.getOrElse(i) { 0 }
            val n2 = arr2.getOrElse(i) { 0 }

            if (n1 > n2) return 1
            if (n1 < n2) return -1
        }
        return 0
    }
}
