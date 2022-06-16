package com.ch05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class PersonTest : DescribeSpec({
    describe("findTheOldest") {
        beforeAny {
            outputStream.reset()
            System.setOut(PrintStream(outputStream))
        }

        afterAny {
            System.setOut(output)
        }

        context("리스트를 입력하면") {
            it("나이가 가장 많은 Person 출력한다") {
                findTheOldest(people)

                outputStream.toString() shouldStartWith people[1].toString()
            }
        }
    }

    describe("isAdult") {
        context("나이가 21 이상인 경우 true 반환") {
            val predicate = Person::isAdult

            listOf(
                Person("홍길동-1", 21),
                Person("홍길동-2", 22),
                Person("홍길동-3", 23),
            ).forAll {
                predicate(it).shouldBeTrue()
            }
        }
    }

    describe("findTheOldestByLambda") {
        context("리스트를 입력하면") {
            it("나이가 가장 많은 Person 반환") {
                findTheOldestByLambda(people) shouldBe people[1]
            }
        }

        context("test") {
            println(people.maxOfOrNull { it.age })
        }
    }

    describe("sum") {
        context("두개의 인자를 입력을 받으면") {
            listOf(1 to 2, 2 to 3, 3 to 4).forAll { (num1, num2) ->
                sum(num1, num2) shouldBe num1 + num2
            }
        }
    }

    describe("getAge") {
        context("가장 나이가 많은 Person 반환") {
            people.maxByOrNull(getAge) shouldBe people[1]
            people.maxByOrNull(Person::age) shouldBe people[1]
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!

        val people = listOf(
            Person("홍길동-1", 15),
            Person("홍길동-2", 20),
            Person("홍길동-3", 12)
        )
    }
}