package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons64064Test : StringSpec({
    "case_01" {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("fr*d*", "abc1**")

        val actual = Lessons64064().solution(user_id, banned_id)

        actual shouldBe 2
    }

    "case_02" {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("*rodo", "*rodo", "******")

        val actual = Lessons64064().solution(user_id, banned_id)

        actual shouldBe 2
    }

    "case_03" {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("fr*d*", "*rodo", "******", "******")

        val actual = Lessons64064().solution(user_id, banned_id)

        actual shouldBe 3
    }
})
