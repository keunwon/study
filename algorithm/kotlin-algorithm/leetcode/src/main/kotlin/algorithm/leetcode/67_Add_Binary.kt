package algorithm.leetcode

class `67_Add_Binary` {
    fun addBinary(a: String, b: String): String {
        val result = StringBuilder(a.length.coerceAtLeast(b.length) + 1)
        var idx1 = a.lastIndex
        var idx2 = b.lastIndex
        var carry = 0

        while (idx1 >= 0 || idx2 >= 0 || carry > 0) {
            val n1 = if (idx1 >= 0) a[idx1] - '0' else 0
            val n2 = if (idx2 >= 0) b[idx2] - '0' else 0
            val sum = n1 + n2 + carry

            carry = sum / 2
            result.append(sum % 2)
            --idx1
            --idx2
        }
        return result.reverse().toString()
    }
}
