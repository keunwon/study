package com.ch02

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class ColorTest: DescribeSpec({

    describe("Color(enum type)") {
        context("색상 한글로 변환") {
            listOf(
                Color.RED to "Richard",
                Color.ORANGE to "Of",
                Color.YELLOW to "York",
                Color.GREEN to "Gave",
                Color.BLUE to "Battle",
                Color.INDIGO to "In",
                Color.VIOLET to "Vain",
            ).forAll { (color: Color, letter: String) ->
                it("$color -> $letter") {
                    getMnemonic(color) shouldBe letter
                }
            }
        }

        context("warm, neutral, cold 중 하나로 변환") {
            listOf(
                Color.RED to "warm",
                Color.ORANGE to "warm",
                Color.YELLOW to "warm",
                Color.GREEN to "neutral",
                Color.BLUE to "cold",
                Color.INDIGO to "cold",
                Color.VIOLET to "cold",
            ).forAll { (color: Color, letter: String) ->
                it("$color -> $letter") {
                    getWarmth(color) shouldBe letter
                }
            }
        }

        context("혼합(mix)") {
            context("알맞은 색으로 섞은 경우") {
                table(
                    headers("첫번째 색상", "두번째 색상", "섞은 색상"),
                    row(Color.RED, Color.YELLOW, Color.ORANGE),
                    row(Color.YELLOW, Color.BLUE, Color.GREEN),
                    row(Color.BLUE, Color.VIOLET, Color.INDIGO),
                ).forAll { c1, c2, result ->
                    it("mix: $c1, $c2 => $result") {
                        mix(c1, c2) shouldBe result
                        mixOptimized(c1, c2) shouldBe result
                    }
                }
            }

            context("잘못된 색으로 섞는 경우") {
                listOf(
                    Color.RED to Color.BLUE,
                    Color.RED to Color.VIOLET,
                    Color.YELLOW to Color.VIOLET,
                    Color.BLUE to Color.RED,
                ).forAll { (first: Color, second: Color) ->
                    it("exception 발생, mix: $first, $second") {
                        shouldThrow<Exception> {
                            mix(first, second)
                        }.message shouldBe "Dirty color"
                    }
                }
            }
        }
    }
})