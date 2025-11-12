package algorithm.programmers

class Lesson140108 {
    fun solution(s: String): Int {
        var x = s[0]
        var count = 1
        var result = 0

        for (i in 1 until s.length) {
            val c = s[i]

            if (count == 0) {
                x = c
                ++count
                continue
            }

            if (c == x) ++count else --count
            if (count == 0) ++result
        }

        if (count > 0) ++result

        return result
    }
}
