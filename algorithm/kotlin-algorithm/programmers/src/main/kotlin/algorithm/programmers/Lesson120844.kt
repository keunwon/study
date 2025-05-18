package algorithm.programmers

class Lesson120844 {
    fun solution(numbers: IntArray, direction: String): IntArray {
        if (direction == "right") {
            val tmp = numbers.last()
            for (i in numbers.lastIndex downTo 1) {
                numbers[i] = numbers[i - 1]
            }
            numbers[0] = tmp
        } else {
            val tmp = numbers[0]
            for (i in 0 until numbers.lastIndex) {
                numbers[i] = numbers[i + 1]
            }
            numbers[numbers.lastIndex] = tmp
        }
        return numbers
    }
}
