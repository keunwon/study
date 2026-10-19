class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val ascii1 = IntArray(128) { -1 }
        val ascii2 = IntArray(128) { -1 }

        for (i in s.indices) {
            val c1 = s[i].code
            val c2 = t[i].code

            if (ascii1[c1] == -1 && ascii2[c2] == -1) {
                ascii1[c1] = i
                ascii2[c2] = i
            } else if (ascii1[c1] != ascii2[c2]) {
                return false
            }
        }
        return true
    }
}