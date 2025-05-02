package algorithm.leetcode

class `58_Length_of_Last_Word` {
    fun lengthOfLastWord(s: String): Int {
        var result = 0
        var flag = false

        for (i in s.lastIndex downTo 0) {
            if (s[i] == ' ' && flag) break
            if (s[i] != ' ') {
                if (!flag) flag = true
                ++result
            }
        }
        return result
    }
}

fun main() {
    `58_Length_of_Last_Word`().lengthOfLastWord("Hello World").also { println(it); } // expect: 5
    `58_Length_of_Last_Word`().lengthOfLastWord("   fly me   to   the moon  ").also { println(it); } // expect: 4
}
