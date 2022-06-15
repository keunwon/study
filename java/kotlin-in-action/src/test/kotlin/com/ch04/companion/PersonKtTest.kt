package com.ch04.companion

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class PersonKtTest : DescribeSpec({

    describe("Person") {

        context("fromJSON") {
            it("json 사용하여 객체를 생성합니다") {
                val json = jacksonObjectMapper().writeValueAsString(Person("홍길동"))
                val person = Person.fromJSON(json)

                person.name shouldBe "홍길동"
            }
        }

        context("fromJson2") {
            it("json 사용하여 객체를 생성합니다") {
                val json = jacksonObjectMapper().writeValueAsString(Person("이순신"))
                val person = Person.fromJson2(json)

                person.name shouldBe "이순신"
            }
        }
    }
})