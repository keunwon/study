package com.ch06

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class NothingKtTest : DescribeSpec({

    describe("fail") {
        context("IllegalStateException 발생합니다") {
            val errorMessage = "오류가 발생하였습니다"

            shouldThrowExactly<IllegalStateException> {
                fail(errorMessage)
            }.message shouldBe errorMessage
        }

        context("주소정보가 존재하지 않으면") {
            it("IllegalStateException 발생합니다") {
                val errorMessage = "No address"

                shouldThrowExactly<java.lang.IllegalStateException> {
                    company.address ?: fail(errorMessage)
                }.message shouldBe errorMessage
            }
        }
    }
}) {
    companion object {
        val company = Company("회사", null)
    }
}
