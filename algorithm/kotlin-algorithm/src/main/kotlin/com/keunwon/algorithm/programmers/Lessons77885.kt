package com.keunwon.algorithm.programmers

/**
 * Title: 2개 이하로 다른 비트
 * Level: 2
 **/
class Lessons77885 {
    fun solution(numbers: LongArray): LongArray {
        return numbers.map { number ->
            if (number % 2 == 0L) number + 1
            else {
                val binary = "0${number.toString(2)}".toCharArray()
                val findIndex = binary.indexOfLast { it == '0' }
                binary[findIndex] = '1'
                binary[findIndex + 1] = '0'
                binary.joinToString("").toLong(2)
            }
        }.toLongArray()
    }
}
