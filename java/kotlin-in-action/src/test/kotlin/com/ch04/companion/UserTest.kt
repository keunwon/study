package com.ch04.companion

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class UserTest : DescribeSpec({

    describe("Person 객체 생성") {
        context("newSubscribingUser") {
            it("이메일 주소를 입력, '@' 이전 문자열을 반환합니다") {
                val user = User.newSubscribingUser("test@google.com")
                user.nickname shouldBe "test"
            }
        }

        context("newFacebookUser") {
            it("아이디를 입력하면, 페이스북 이름을 반환합니다") {
                val user = User.newFacebookUser(4)
                user.nickname shouldBe "fb:4"
            }
        }
    }
})