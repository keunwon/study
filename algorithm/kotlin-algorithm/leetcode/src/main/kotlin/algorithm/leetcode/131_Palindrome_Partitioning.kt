package algorithm.leetcode

class `131_Palindrome_Partitioning` {
    fun partition(s: String): List<List<String>> {
        return mutableListOf<List<String>>().apply { dfs(this, s, 0, mutableListOf()) }
    }

    private fun dfs(
        dest: MutableList<List<String>>,
        s: String,
        startIndex: Int,
        list: MutableList<String>,
    ) {
        if (startIndex == s.length) {
            dest.add(list.toList())
            return
        }

        for (end in startIndex until s.length) {
            if (isPalindrome(s, startIndex, end)) {
                list.add(s.substring(startIndex, end + 1))
                dfs(dest, s, end + 1, list)
                list.removeLast()
            }
        }
    }

    private fun isPalindrome(s: String, left: Int, right: Int): Boolean {
        var l = left
        var r = right
        while (l < r) {
            if (s[l++] != s[r--]) return false
        }
        return true
    }
}
