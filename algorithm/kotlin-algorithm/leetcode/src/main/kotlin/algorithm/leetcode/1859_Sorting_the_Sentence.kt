package algorithm.leetcode

class `1859_Sorting_the_Sentence` {
    fun sortSentence(s: String): String {
        return s.split(" ")
            .sortedBy { it.takeLast(1) }
            .joinToString(" ") { it.take(it.lastIndex) }
    }
}

fun main() {
    `1859_Sorting_the_Sentence`().sortSentence("is2 sentence4 This1 a3").also { println(it) } // This is a sentence
    `1859_Sorting_the_Sentence`().sortSentence("Myself2 Me1 I4 and3").also { println(it) } // Me Myself and I
}
