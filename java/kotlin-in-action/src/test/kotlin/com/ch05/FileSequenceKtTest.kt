package com.ch05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.io.File

internal class FileSequenceKtTest : DescribeSpec({

    describe("isInsideHiddenDirectory") {
        context("파일 경로를 입력하면") {
            it("경로에 hidden 속성이 존재하는 경우 true 반환합니다") {
                fileToHidden.isInsideHiddenDirectory().shouldBeTrue()
            }

            it("경로에 hidden 속성이 존재하지 않으면 false 반환합니다") {
                fileToNotHidden.isInsideHiddenDirectory().shouldBeFalse()
            }
        }
    }
}) {
    companion object {
        val fileToHidden = File("/Users/developer/Desktop/study/java/kotlin-in-action/.hidden")
        val fileToNotHidden = File("/Users/developer/Desktop/study/java/kotlin-in-action")
    }
}
