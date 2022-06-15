package com.ch04.companion

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class PersonKtTest : DescribeSpec({

    describe("Person") {
        context("json 이요하여 객체 생성") {
            val json = objectMapper.writeValueAsString(Person("홍길동"))
            val person = Person.fromJSON(json)
            
            person.name shouldBe "홍길동"
        }
    }
}) {
    companion object {
        val objectMapper = ObjectMapper().registerKotlinModule()
    }
}