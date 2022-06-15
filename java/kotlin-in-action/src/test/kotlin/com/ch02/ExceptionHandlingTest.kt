package com.ch02

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import java.io.BufferedReader
import java.io.StringReader

internal class ExceptionHandlingTest : DescribeSpec({

    describe("예외 처리 핸들링") {
        context("문자열 숫자 읽기") {
            listOf("239").forAll { number ->
                val br = BufferedReader(StringReader(number))
                readNumber(br) shouldBe number.toInt()
            }
        }

        context("숫자가 아닌 문자열 읽기") {
            listOf("abc", "bcd", "cde").forAll { letter ->
                val br = BufferedReader(StringReader(letter))
                readNumber(br) shouldBe null
            }
        }
    }
})