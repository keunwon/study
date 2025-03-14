package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem7568Test : StringSpec({
    "case-1" {
        val peoples = arrayOf(
            intArrayOf(55, 185),
            intArrayOf(58, 183),
            intArrayOf(88, 186),
            intArrayOf(60, 175),
            intArrayOf(46, 155),
        )
        val actual = Problem7568().solution(peoples)
        actual shouldBe intArrayOf(2, 2, 1, 2, 5)
    }
})
