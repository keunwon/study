package com.ch07

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class NameComponentsKtTest : DescribeSpec({

    describe("splitFilename") {
        it("파일명을 입력하면 NameComponents 반환합니다") {
            val name = "파일명"
            val extension = "doc"

            splitFilename("$name.$extension") shouldBe NameComponents(name, extension)
        }
    }
})
