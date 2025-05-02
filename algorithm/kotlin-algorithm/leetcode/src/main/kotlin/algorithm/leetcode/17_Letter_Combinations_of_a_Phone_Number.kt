package algorithm.leetcode

class `17_Letter_Combinations_of_a_Phone_Number` {
    private val phone = arrayOf(
        arrayOf(),
        arrayOf(),
        arrayOf('a', 'b', 'c'),
        arrayOf('d', 'e', 'f'),
        arrayOf('g', 'h', 'i'),
        arrayOf('j', 'k', 'l'),
        arrayOf('m', 'n', 'o'),
        arrayOf('p', 'q', 'r', 's'),
        arrayOf('t', 'u', 'v'),
        arrayOf('w', 'x', 'y', 'z'),
    )
    private val result = mutableListOf<String>()

    fun letterCombinations(digits: String): List<String> {
        dfs(0, StringBuilder(digits.length), digits)
        return result
    }

    private fun dfs(depth: Int, key: StringBuilder, digits: String) {
        if (depth == digits.length) {
            if (key.isNotEmpty()) result.add(key.toString())
            return
        }

        val pIdx = digits[depth] - '0'
        phone[pIdx].forEach { c ->
            key.append(c)
            dfs(depth + 1, key, digits)
            key.deleteCharAt(key.lastIndex)
        }
    }
}
