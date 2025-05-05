package algorithm.leetcode

class `290_Word_Pattern` {
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")
        if (pattern.length != words.size) return false

        val map = mutableMapOf<Char, String>()
        for ((i, c) in pattern.withIndex()) {
            val word = words[i]
            if (!map.contains(c) && !map.containsValue(word)) {
                map[c] = word
            } else if (map[c] != word) {
                return false
            }
        }
        return true
    }
}
