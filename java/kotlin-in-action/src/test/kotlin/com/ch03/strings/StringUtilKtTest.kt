package com.ch03.strings

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class StringUtilKtTest : DescribeSpec({

    describe("문자열 관련 유틸 함수") {

        context("문자열의 마지막 문자 반환") {
            listOf(
                "abc" to 'c',
                "bcd" to 'd',
                "cde" to 'e',
                "kotlin" to 'n',
            ).forAll { (text: String, letter: Char) ->
                it ("[확장 함수] 문자열: $text, 마지막 문자: $letter") {
                    text.lastChar() shouldBe letter
                }
                it ("[확장 프로퍼티] 문자열: $text, 마지막 문자: $letter") {
                    text.lastChar shouldBe letter
                }
            }
        }

        context("문자열의 마지막 단어 변경") {
            listOf(
                "abc" to '!',
                "abc" to 'd',
                "zzz" to 'Z',
            ).forAll { (text: String, letter: Char) ->
                val sb = StringBuilder(text)
                sb.lastChar = letter
                sb.lastChar shouldBe letter
            }
        }
    }
})