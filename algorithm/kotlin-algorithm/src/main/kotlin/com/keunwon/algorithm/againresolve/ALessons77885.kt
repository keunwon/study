package com.keunwon.algorithm.againresolve

class ALessons77885 {
    fun solution(numbers: LongArray): LongArray {
        return numbers.map { n ->
            if (n % 2 == 0L) n + 1
            else {
                val binary = "0${n.toString(2)}".toCharArray()
                val findIndex = binary.indexOfLast { it == '0' }
                binary[findIndex] = '1'
                binary[findIndex + 1] = '0'
                binary.joinToString("").toLong(2)
            }
        }.toLongArray()
    }
}

fun main() {
    ALessons77885().solution(
        longArrayOf(2, 7, 9)
    ).let { println(it.contentToString()) }
}
