package algorithm.leetcode

class `819_Most_Common_Word` {
    fun mostCommonWord(paragraph: String, banned: Array<String>): String {
        var str = paragraph.lowercase().replace("""[!?',;.]""".toRegex(), " ")
        val map = mutableMapOf<String, Int>()
        var max = 0
        var maxKey = ""

        val words = str.split(" ").filter { it.isNotBlank() }
        for (word in words) {
            if (banned.contains(word)) continue

            map[word] = map.getOrDefault(word, 0) + 1
            if (max < map.getValue(word)) {
                max = map.getValue(word)
                maxKey = word
            }
        }
        return maxKey
    }
}

fun main() {
    `819_Most_Common_Word`().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", arrayOf("hit"))
        .also { println(it) }
}
