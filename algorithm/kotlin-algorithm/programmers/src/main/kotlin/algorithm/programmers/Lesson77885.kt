package algorithm.programmers

class Lesson77885 {
    fun solution(numbers: LongArray): LongArray {
        return numbers.map { n ->
            if (n % 2 == 0L) n + 1
            else {
                val str = "0${n.toString(2)}"
                val binary = str.toCharArray()
                val index = binary.indexOfLast { it == '0' }
                binary[index] = '1'
                binary[index + 1] = '0'
                binary.joinToString("").toLong(2)
            }
        }.toLongArray()
    }
}
