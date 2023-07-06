package com.keunwon.algorithm.programmers

/**
 * Title: 소수 만들기
 * Level: 1
 **/
class Lessons12977 {
    fun solution(nums: IntArray): Int {
        var answer = 0
        for (i in 0 until nums.size - 2) {
            for (j in i + 1 until nums.size - 1) {
                for (k in j + 1 until nums.size) {
                    val sum = nums[i] + nums[j] + nums[k]
                    if (isPrime(sum)) answer++
                }
            }
        }
        return answer
    }

    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        return (2 until n).all { n % it != 0 }
    }
}
