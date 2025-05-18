package algorithm.programmers

class Lesson136798 {
    fun solution(number: Int, limit: Int, power: Int): Int {
        val arr = IntArray(number + 1)
        for (i in 1..number) {
            for (j in i..number step i) {
                ++arr[j]
            }
        }
        return arr.sumOf { n -> if (n > limit) power else n }
    }
}
