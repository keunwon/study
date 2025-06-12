package algorithm.programmers

class Lesson132267 {
    fun solution(a: Int, b: Int, n: Int): Int {
        var result = 0
        var ret = n

        while (ret >= a) {
            val div = ret / a
            val mod = ret % a

            result += div * b
            ret = div * b + mod
        }

        return result
    }
}
