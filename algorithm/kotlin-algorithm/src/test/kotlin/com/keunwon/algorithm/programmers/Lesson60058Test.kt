package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson60058Test : StringSpec({
    "case-1" {
        val p = "(()())()"
        val actual = Lesson60058().solution(p)
        actual shouldBe "(()())()"
    }

    "case-2" {
        val p = ")("
        val actual = Lesson60058().solution(p)
        actual shouldBe "()"
    }

    "case-3" {
        val p = "()))((()"
        val actual = Lesson60058().solution(p)
        actual shouldBe "()(())()"
    }
})