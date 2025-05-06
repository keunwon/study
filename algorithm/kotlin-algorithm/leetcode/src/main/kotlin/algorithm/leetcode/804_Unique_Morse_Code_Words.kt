package algorithm.leetcode

class `804_Unique_Morse_Code_Words` {
    private val codes = arrayOf(
        ".-",
        "-...",
        "-.-.",
        "-..",
        ".",
        "..-.",
        "--.",
        "....",
        "..",
        ".---",
        "-.-",
        ".-..",
        "--",
        "-.",
        "---",
        ".--.",
        "--.-",
        ".-.",
        "...",
        "-",
        "..-",
        "...-",
        ".--",
        "-..-",
        "-.--",
        "--..",
    )

    fun uniqueMorseRepresentations(words: Array<String>): Int {
        val set = HashSet<String>(words.size)
        for (word in words) {
            val sb = StringBuilder(word.length * 4)
            for (c in word) {
                sb.append(codes[c - 'a'])
            }
            set.add(sb.toString())
        }
        return set.size
    }
}

fun main() {
    `804_Unique_Morse_Code_Words`().uniqueMorseRepresentations(arrayOf("gin", "zen", "gig", "msg"))
        .also { println(it) }
}
