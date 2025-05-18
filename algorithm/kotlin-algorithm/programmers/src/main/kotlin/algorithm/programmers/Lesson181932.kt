package algorithm.programmers

class Lesson181932 {
    fun solution(code: String): String {
        val ret = StringBuilder()
        var mode = 0

        for ((i, c) in code.withIndex()) {
            if (mode == 0) {
                if (c == '1') mode = 1
                else if (i % 2 == 0) ret.append(c)
            } else {
                if (c == '1') mode = 0
                else if (i % 2 == 1) ret.append(c)
            }
        }

        return ret.toString().ifBlank { "EMPTY" }
    }
}
