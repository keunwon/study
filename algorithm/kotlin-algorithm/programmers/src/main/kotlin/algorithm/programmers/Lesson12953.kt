package algorithm.programmers

class Lesson12953 {
    fun solution(arr: IntArray): Int {
        return arr.reduce { acc, n -> (acc * n) / gcd(acc, n) }
    }

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
