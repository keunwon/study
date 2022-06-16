package com.ch05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSingleElement
import io.kotest.matchers.maps.shouldContainExactly

internal class PersonCollectionApiTest : DescribeSpec({

    describe("컬렉션 함수형 API") {
        context("filter") {
            people.filter { it.age > 30 }
                .shouldHaveSingleElement(Person("Bob", 31))
        }

        context("map") {
            it("lambda") {
                people.map { it.name }.shouldContainExactly("Alice", "Bob")
            }
            it("member reference") {
                people.map(Person::name).shouldContainExactly("Alice", "Bob")
            }
            it("영문자를 대문자로 변경하고 반환합니다") {
                val numbers = mapOf(0 to "zero", 1 to "one")
                numbers.mapValues { it.value.uppercase() }
                    .shouldContainExactly(mapOf(0 to "ZERO", 1 to "ONE"))
            }
        }

        context("filter + map") {
            it("나이가 30 초과한 사람의 이름을 반환합니다") {
                people.filter { it.age > 30 }.map(Person::name)
                    .shouldHaveSingleElement("Bob")
            }
        }
    }
}) {
    companion object {
        val people = listOf(Person("Alice", 29), Person("Bob", 31))
    }
}