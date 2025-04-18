package algorithm.programmers

class Lesson148653 {
    fun solution(storey: Int): Int {
        var answer = 0
        var n = storey

        while (n != 0) {
            val mod = n % 10
            when {
                mod < 5 -> answer += mod

                mod > 5 -> {
                    answer += 10 - mod
                    n += 10
                }

                else -> {
                    answer += 5
                    val next = n / 10 % 10
                    if (5 <= next) n += 10
                }
            }
            n /= 10
        }
        return answer
    }
}
