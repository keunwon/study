package com.ch03

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

internal class UserKtTest : DescribeSpec({

    describe("saveUser") {
        it("사용자 저장") {
            val user = User(1, "홍길동", "서울 특별시")
            shouldNotThrowAny { saveUser(user) }
        }

        context("이름이 빈 공백인 경우") {
            it("IllegalArgumentException 예외를 던진다") {
                val user = User(1, "", "서울 특별시")

                shouldThrow<IllegalArgumentException> { saveUser(user) }
                    .message shouldBe "Can't save user ${user.id}: empty Name"
            }
        }

        context("주소가 빈 공백인 경우") {
            it("IllegalArgumentException 예외를 던진다") {
                val user = User(1, "홍길동", "")

                shouldThrow<IllegalArgumentException> { saveUser(user) }
                    .message shouldBe "Can't save user ${user.id}: empty Address"
            }
        }
    }
})