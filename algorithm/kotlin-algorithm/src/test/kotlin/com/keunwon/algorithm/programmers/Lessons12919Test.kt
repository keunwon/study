package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons12919Test : StringSpec({
    "case" {
        val seoul = arrayOf("Jane", "Kim")

        val actual = Lessons12919().solution(seoul)

        actual shouldBe "김서방은 1에 있다"
    }
})
