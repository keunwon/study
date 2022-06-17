package com.ch05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSingleElement
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class PersonCollectionApiTest : DescribeSpec({

    describe("컬렉션 함수형 API") {
        context("filter") {
            it("나이가 30 초과하는 사람을 반환합니다") {
                people.filter { it.age > 30 }
                    .shouldHaveSingleElement(Person("Bob", 31))
            }
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

        context("all") {
            it("모든 사람의 나이가 27 이하인 경우 true 반환합니다") {
                val persons = listOf(Person("Alice", 27), Person("Bob", 20))

                persons.all(canBeInClub27).shouldBeTrue()
            }
        }

        context("any") {
            it("1명 이상의 사람의 나이가 27 이하인 경우 true 반환합니다") {
                val persons = people + Person("홍길동", 27)

                persons.any(canBeInClub27).shouldBeTrue()
            }
        }

        context("count") {
            it("27살 이하 사람 수를 반환합니다") {
                val persons = listOf(
                    Person("김길동", 10),
                    Person("나길동", 20),
                    Person("다길동", 27),
                    Person("라길동", 35),
                    Person("마길동", 35)
                )

                persons.count(canBeInClub27) shouldBe 3
            }
        }

        context("find") {
            it("나이가 27살 이하인 한 사람을 찾아 반환합니다") {
                val person = listOf(
                    Person("Alice", 27),
                    Person("Bob", 31)
                )

                person.find(canBeInClub27)
                    .shouldNotBeNull()
                    .shouldBe(person[0])
            }
        }

        context("group") {
            val person = listOf(
                Person("Alice", 31),
                Person("Bob", 29),

                Person("Carol", 31)
            )
            val personMap = person.groupBy { it.age }

            personMap[31] shouldContainExactlyInAnyOrder listOf(person[0], person[2])
            personMap[29] shouldContainExactlyInAnyOrder listOf(person[1])
        }
    }
}) {
    companion object {
        val people = listOf(Person("Alice", 29), Person("Bob", 31))
    }
}
