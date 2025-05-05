package algorithm.leetcode

class `205_Isomorphic_Strings` {
    fun isIsomorphic(s: String, t: String): Boolean {
        val arr1 = IntArray(256) { -1 }
        val arr2 = IntArray(256) { -1 }

        for (i in s.indices) {
            val c1 = s[i].code
            val c2 = t[i].code

            if (arr1[c1] == -1) arr1[c1] = i
            if (arr2[c2] == -1) arr2[c2] = i

            if (arr1[c1] != arr2[c2]) return false
        }
        return true
    }
}

fun main() {
    `205_Isomorphic_Strings`().isIsomorphic("paper", "title").also { println(it); }
}
