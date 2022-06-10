package com.ch03

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

internal class RegexKtTest : DescribeSpec({

    describe("toRegexArray") {
        context("문자열에 '.', '-' 기준으로 List 생성") {
            it("정규식을 이용") {
                regexText.forAll { (text, list) ->
                    toRegexArray(text) shouldContainExactly list
                }
            }

            it("구분 문자(열) 이용") {
                regexText.forAll { (text, list) ->
                    toRegexArray2(text) shouldContainExactly list
                }
            }
        }
    }

    describe("parsePath") {
        context("파일 전체 경로가 입력되면") {
            it("디렉토리, 파일명, 확장자가 반환된다") {
                table(
                    headers("전체 경로", "디렉토리", "파일명", "확장자"),
                    row(
                        "/Users/developer/Desktop/study/java/kotlin-in-action/test-1.adoc",
                    "/Users/developer/Desktop/study/java/kotlin-in-action",
                    "test-1",
                    "adoc"),
                    row(
                        "/Users/developer/Desktop/study/java/kotlin-in-action/test-2.adoc",
                        "/Users/developer/Desktop/study/java/kotlin-in-action",
                        "test-2",
                        "adoc"
                    )
                ).forAll { fullPath, directory, fileName, extension ->
                    parsePath(fullPath) should {
                        it["directory"] shouldBe directory
                        it["fileName"] shouldBe fileName
                        it["extension"] shouldBe extension
                    }
                    
                    parseRegexPath(fullPath) should {
                        it["directory"] shouldBe directory
                        it["fileName"] shouldBe fileName
                        it["extension"] shouldBe extension
                    }
                }
            }
        }
    }

}) {
    companion object {
        val regexText = listOf(
            "12.345-6.A" to listOf("12", "345", "6", "A"),
            "1-1-235.6-A" to listOf("1", "1", "235", "6", "A")
        )
    }
}