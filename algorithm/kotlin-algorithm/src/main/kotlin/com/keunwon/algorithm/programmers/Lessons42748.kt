package com.keunwon.algorithm.programmers

/**
 * Title: K번째수
 * Level: 1
 **/
class Lessons42748 {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        return commands.map { (i, j, k) ->
            array.slice(i - 1 until j).sorted()[k - 1]
        }.toIntArray()
    }
}
