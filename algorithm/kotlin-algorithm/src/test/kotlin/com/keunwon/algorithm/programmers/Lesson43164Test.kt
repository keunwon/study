package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson43164Test : StringSpec({
    "case-1" {
        val tickets = arrayOf(
            arrayOf("ICN", "JFK"),
            arrayOf("HND", "IAD"),
            arrayOf("JFK", "HND")
        )
        val actual = Lesson43164().solution(tickets)
        actual shouldBe arrayOf("ICN", "JFK", "HND", "IAD")
    }

    "case-2" {
        val tickets = arrayOf(
            arrayOf("ICN", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("SFO", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO")
        )
        val actual = Lesson43164().solution(tickets)
        actual shouldBe arrayOf("ICN", "ATL", "ICN", "SFO", "ATL", "SFO")
    }
})