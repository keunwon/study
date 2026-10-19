class Solution {
    fun reverseWords(s: String): String {
        val sb = StringBuilder(s.length)
        var idx = s.lastIndex

        while (idx >= 0) {
            while (idx >= 0 && s[idx] == ' ') {
                --idx
            }

            val endIdx = idx
            while (idx >= 0 && s[idx] != ' ') {
                --idx
            }

            if (idx < endIdx) {
                if (sb.isNotEmpty()) sb.append(' ')
                sb.append(s.substring(idx + 1, endIdx + 1))
            }
        }
        return sb.toString()
    }
}