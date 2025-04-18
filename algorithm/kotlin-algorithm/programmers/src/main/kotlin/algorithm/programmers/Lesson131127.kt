package algorithm.programmers

class Lesson131127 {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val target = want.zip(number.toList())
        var answer = 0

        for (i in 0..discount.size - 10) {
            val countMap = discount.slice(i until i + 10)
                .groupingBy { it }
                .eachCount()
            val valid = target.all { (w, n) -> countMap.getOrDefault(w, 0) == n }

            if (valid) ++answer
        }
        return answer
    }
}
