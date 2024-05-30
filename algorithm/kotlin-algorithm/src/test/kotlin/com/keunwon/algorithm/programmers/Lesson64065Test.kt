package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson64065Test : StringSpec({
    "case-1" {
        val s = "{{2},{2,1},{2,1,3},{2,1,3,4}}"
        val actual = Lesson64065().solution(s)
        actual shouldBe intArrayOf(2, 1, 3, 4)
    }

    "case-2" {
        val s = "{{1,2,3},{2,1},{1,2,4,3},{2}}"
        val actual = Lesson64065().solution(s)
        actual shouldBe intArrayOf(2, 1, 3, 4)
    }

    "case-3" {
        val s = "{{20,111},{111}}"
        val actual = Lesson64065().solution(s)
        actual shouldBe intArrayOf(111, 20)
    }

    "case-4" {
        val s = "{{123}}"
        val actual = Lesson64065().solution(s)
        actual shouldBe intArrayOf(123)
    }

    "case-5" {
        val s = "{{4,2,3},{3},{2,3,4,1},{2,3}}"
        val actual = Lesson64065().solution(s)
        actual shouldBe intArrayOf(3, 2, 4, 1)
    }
})
