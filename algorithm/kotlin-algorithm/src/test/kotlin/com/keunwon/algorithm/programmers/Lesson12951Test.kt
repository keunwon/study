package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12951Test : StringSpec({
    "case-1" {
        val s = "3people unFollowed me"
        val actual = Lesson12951().solution(s)
        actual shouldBe "3people Unfollowed Me"
    }

    "case-2" {
        val s = "for the last week"
        val actual = Lesson12951().solution(s)
        actual shouldBe "For The Last Week"
    }
})
