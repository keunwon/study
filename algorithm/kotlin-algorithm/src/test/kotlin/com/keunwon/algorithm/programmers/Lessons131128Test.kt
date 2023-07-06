package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons131128Test : StringSpec({
    "case" {
        forAll(
            row("100", "2345", "-1"),
            row("100", "203045", "0"),
            row("100", "123450", "10"),
            row("12321", "42531", "321"),
            row("5525", "1255", "552"),
        ) { x, y, result ->
            val actual = Lessons131128().solution(x, y)
            actual shouldBe result
        }
    }
})
