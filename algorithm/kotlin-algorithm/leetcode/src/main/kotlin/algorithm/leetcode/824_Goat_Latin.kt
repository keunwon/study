package algorithm.leetcode

class `824_Goat_Latin` {
    fun toGoatLatin(sentence: String): String {
        val sb = StringBuilder()
        val words = sentence.split(" ")
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')

        for ((i, word) in words.withIndex()) {
            if (vowels.contains(word[0].lowercaseChar())) {
                sb.append(word)
            } else {
                sb.append(word.substring(1))
                sb.append(word[0])
            }
            sb.append("ma")
            sb.append("a".repeat(i + 1))

            if (i < words.lastIndex) sb.append(' ')
        }
        return sb.toString()
    }
}

fun main() {
    `824_Goat_Latin`().toGoatLatin("I speak Goat Latin").also { println(it) }
}
