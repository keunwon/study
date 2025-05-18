package algorithm.programmers

class Lesson142086 {
    fun solution(s: String): IntArray {
        val alphabet = IntArray(26) { -1 }
        val result = IntArray(s.length)

        for ((i, c) in s.withIndex()) {
            result[i] = if (alphabet[c - 'a'] == -1) -1 else i - alphabet[c - 'a']
            alphabet[c - 'a'] = i
        }
        return result
    }
}
