package com.keunwon.algorithm.againresolve

class ALessons77885 {
    fun solution(numbers: LongArray): LongArray {
        return numbers.map { number ->
            if (number % 2 == 0L) number + 1
            else {
                val binary = "0${number.toString(2)}".toCharArray()
                val lastIndex = binary.indexOfLast { it == '0' }
                binary[lastIndex] = '1'
                binary[lastIndex + 1] = '0'
                binary.joinToString("").toLong(2)
            }
        }.toLongArray()
    }
}
