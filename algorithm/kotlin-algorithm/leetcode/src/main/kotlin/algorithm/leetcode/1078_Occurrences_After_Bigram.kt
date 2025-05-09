package algorithm.leetcode

class `1078_Occurrences_After_Bigram` {
    fun findOcurrences(text: String, first: String, second: String): Array<String> {
        val result = mutableListOf<String>()
        val words = text.split(" ")

        for (i in 2 until words.size) {
            if (words[i - 2] == first && words[i - 1] == second) {
                result.add(words[i])
            }
        }
        return result.toTypedArray()
    }
}

fun main() {
    `1078_Occurrences_After_Bigram`().findOcurrences("alice is aa good girl she is a good student", "a", "good")
        .also { println(it) }
}
