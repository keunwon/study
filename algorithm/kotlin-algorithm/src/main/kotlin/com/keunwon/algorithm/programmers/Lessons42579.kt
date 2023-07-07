package com.keunwon.algorithm.programmers

/**
 * Title: 베스트앨범
 * Level: 3
 **/
class Lessons42579 {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices.groupBy { genres[it] }
            .toList()
            .sortedByDescending { (_, nums) -> nums.sumOf { plays[it] } }
            .flatMap { (_, nums) -> nums.sortedByDescending { plays[it] }.take(2) }
            .toIntArray()
    }
}
