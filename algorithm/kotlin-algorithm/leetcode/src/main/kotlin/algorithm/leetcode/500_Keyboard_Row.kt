package algorithm.leetcode

class `500_Keyboard_Row` {
    fun findWords(words: Array<String>): Array<String> {
        val rows = arrayOf(
            "qwertyuiop",
            "asdfghjkl",
            "zxcvbnm",
        )
        return words.filter { w ->
            val word = w.lowercase()
            rows.any { row -> word.all { it in row } }
        }.toTypedArray()
    }
}
