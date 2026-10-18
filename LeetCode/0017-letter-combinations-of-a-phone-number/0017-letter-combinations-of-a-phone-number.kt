class Solution {
    private val phone = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
    private val res = mutableListOf<String>()

    fun letterCombinations(digits: String): List<String> {
        dfs(CharArray(digits.length), digits, 0)
        return res
    }

    private fun dfs(pick: CharArray, digits: String, depth: Int) {
        if (depth == pick.size) {
            res.add(String(pick))
            return
        }

        val digit = digits[depth] - '0'
        for (c in phone[digit]) {
            pick[depth] = c
            dfs(pick, digits, depth + 1)
        }
    }
}