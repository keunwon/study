package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12948Test : StringSpec({
    "case" {
        forAll(
            row("01033334444", "*******4444"),
            row("027778888", "*****8888"),
        ) { phone_number, result ->
            val actual = Lessons12948().solution(phone_number)
            actual shouldBe result
        }
    }
})
