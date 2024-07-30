package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson118666Test : StringSpec({
    "case-1" {
        val survey = arrayOf("AN", "CF", "MJ", "RT", "NA")
        val choices = intArrayOf(5, 3, 2, 7, 5)

        val actual = Lesson118666().solution(survey, choices)

        actual shouldBe "TCMA"
    }

    "case-2" {
        val survey = arrayOf("TR", "RT", "TR")
        val choices = intArrayOf(7, 1, 3)

        val actual = Lesson118666().solution(survey, choices)

        actual shouldBe "RCJA"
    }
})
