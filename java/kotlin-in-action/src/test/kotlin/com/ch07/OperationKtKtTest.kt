package com.ch07

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class OperationKtKtTest : DescribeSpec({

    describe("Char.times") {
        it("문자의 곱셈의 수만큼 동일한 문자를 문자열로 반환합니다") {
            (letter * 3) shouldBe "aaa"
        }
    }
}) {
    companion object {
        val letter = 'a'
    }
}
