package com.ch04.companion

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class PersonKtTest : DescribeSpec({

    describe("Person") {
        context("json 사용하여 객체를 생성합니다") {
            it("fromJson") {
                val person = Person.fromJSON(personJson)
                person.name shouldBe "홍길동"
            }

            it("fromJson2") {
                val person = Person.fromJson2(personJson)
                person.name shouldBe "홍길동"
            }

            it("personJsonFactory") {
                val person = personJsonFactory.fromJSON(personJson)
                person.name shouldBe "홍길동"
            }
        }
    }
}) {
    companion object {
        val personJson: String = jacksonObjectMapper().writeValueAsString(Person("홍길동"))
    }
}