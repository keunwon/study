package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem4659Test : StringSpec({
    "case" {
        forAll(
            row("a", "<a> is acceptable."),
            row("tv", "<tv> is not acceptable."),
            row("ptoui", "<ptoui> is not acceptable."),
            row("bontres", "<bontres> is not acceptable."),
            row("zoggax", "<zoggax> is not acceptable."),
            row("wiinq", "<wiinq> is not acceptable."),
            row("eep", "<eep> is acceptable."),
            row("houctuh", "<houctuh> is acceptable."),
        ) { word, result ->
            val actual = Problem4659().solution(word).let { check ->
                "<$word> is " + if (check) "acceptable." else "not acceptable."
            }
            actual shouldBe result
        }
    }
})
