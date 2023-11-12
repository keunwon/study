package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem12933Test : StringSpec({
    "case" {
        forAll(
            row("quqacukqauackck", 2),
            row("kcauq", -1),
            row("quackquackquackquackquackquackquackquackquackquack", 1),
            row("qqqqqqqqqquuuuuuuuuuaaaaaaaaaacccccccccckkkkkkkkkk", 10),
            row("quqaquuacakcqckkuaquckqauckack", 3),
            row("quackqauckquack", -1),
        ) { text, result ->
            val actual = Problem12933().solution(text)
            actual shouldBe result
        }
    }
})
