package com.keunwon.algorithm.programmers

/**
 * Title: 행렬의 덧셈
 * Level: 1
 **/
class Lessons12950 {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        return Array(arr1.size) { r ->
            IntArray(arr1[0].size) { c -> arr1[r][c] + arr2[r][c] }
        }
    }
}
