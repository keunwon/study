package algorithm.programmers

class Lesson120878 {
    fun solution(a: Int, b: Int): Int {
        var ret = b / gcd(a, b)
        while (ret > 0) {
            ret /= if (ret % 2 == 0) {
                2
            } else if (ret % 5 == 0) {
                5
            } else {
                break
            }
        }
        return if (ret == 1) 1 else 2
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}
