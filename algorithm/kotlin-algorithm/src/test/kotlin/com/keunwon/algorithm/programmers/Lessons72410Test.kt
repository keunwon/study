package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons72410Test : StringSpec({
    "case_01" {
        forAll(
            row("...!@BaT#*..y.abcdefghijklm", "bat.y.abcdefghi"),
            row("z-+.^.", "z--"),
            row("=.=", "aaa"),
            row("123_.def", "123_.def"),
            row("abcdefghijklmn.p", "abcdefghijklmn")
        ) { new_id, result ->
            val actual = Lessons72410().solution(new_id)
            actual shouldBe result
        }
    }
})
