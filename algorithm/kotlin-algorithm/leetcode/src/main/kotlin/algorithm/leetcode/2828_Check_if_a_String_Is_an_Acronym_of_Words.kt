package algorithm.leetcode

class `2828_Check_if_a_String_Is_an_Acronym_of_Words` {
    fun isAcronym(words: List<String>, s: String): Boolean {
        if (words.size != s.length) return false
        return words.zip(s.toList()).all { (w, c) -> w[0] == c }
    }
}

fun main() {
    `2828_Check_if_a_String_Is_an_Acronym_of_Words`().isAcronym(
        listOf("alice", "bob", "charlie"),
        "abc"
    ).also { println(it) }

    `2828_Check_if_a_String_Is_an_Acronym_of_Words`().isAcronym(
        listOf(),
        "abc"
    ).also { println(it) }
}
