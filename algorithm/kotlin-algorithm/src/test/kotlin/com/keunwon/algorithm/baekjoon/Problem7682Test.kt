package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem7682Test : StringSpec({
    "case" {
        forAll(
            row("XXXOO.XXX", "invalid"),
            row("XOXOXOXOX", "valid"),
            row("OXOXOXOXO", "invalid"),
            row("XXOOOXXOX", "valid"),
            row("XO.OX...X", "valid"),
            row(".XXX.XOOO", "invalid"),
            row("X.OO..X..", "invalid"),
            row("OOXXXOOXO", "invalid"),
        ) { str, result ->
            val actual = Problem7682().solution(str)
            actual shouldBe result
        }
    }
})
