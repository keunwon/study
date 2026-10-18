class Solution {
    fun addBinary(a: String, b: String): String {
        var idxA = a.lastIndex
        var idxB = b.lastIndex
        var carry = 0
        val sb = StringBuilder()

        while (idxA >= 0 || idxB >= 0 || carry > 0) {
            val n1 = if (idxA >= 0) a[idxA--] - '0' else 0
            val n2 = if (idxB >= 0) b[idxB--] - '0' else 0
            val sum = n1 + n2 + carry

            sb.append(sum % 2)
            carry = sum / 2
        }
        return sb.apply { reverse() }.toString()
    }
}