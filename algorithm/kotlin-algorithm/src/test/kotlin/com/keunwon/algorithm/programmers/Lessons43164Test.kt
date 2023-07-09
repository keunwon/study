package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons43164Test : StringSpec({
    "case_01" {
        val tickets = arrayOf(
            arrayOf("ICN", "JFK"),
            arrayOf("HND", "IAD"),
            arrayOf("JFK", "HND"),
        )

        val actual = Lessons43164().solution(tickets)

        actual shouldBe arrayOf("ICN", "JFK", "HND", "IAD")
    }

    "case_02" {
        val tickets = arrayOf(
            arrayOf("ICN", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("SFO", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO"),
        )

        val actual = Lessons43164().solution(tickets)

        actual shouldBe arrayOf("ICN", "ATL", "ICN", "SFO", "ATL", "SFO")
    }
})
