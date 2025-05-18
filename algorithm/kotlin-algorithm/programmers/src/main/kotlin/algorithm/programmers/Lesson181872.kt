package algorithm.programmers

class Lesson181872 {
    fun solution(myString: String, pat: String): String {
        val lastIndex = myString.lastIndexOf(pat)
        return myString.substring(0, lastIndex + pat.length)
    }
}
