package com.ch06

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class PersonTest : DescribeSpec({

    describe("countryName") {
        it("국가명이 존재하지 않으면 'Unknown'을 반환합니다") {
            val person = Person("Dmitry", null)

            person.countryName() shouldBe "Unknown"
        }

        it("국가명을 반환합니다") {
            person.countryName() shouldBe address.country
        }
    }

    describe("printShippingBabel") {
        beforeEach {
            outputStream.reset()
            System.setOut(PrintStream(outputStream))
        }

        afterEach {
            System.setOut(output)
        }

        context("Person 입력하면") {
            it("주소가 존재하지 않으면 IllegalArgumentException 발생합니다") {
                val exception = shouldThrowExactly<IllegalArgumentException> {
                    val person = Person("Alexey", null)
                    printShippingBabel(person)
                }

                exception.message shouldBe "No Address"
            }

            it("주소 정보가 출력됩니다") {
                printShippingBabel(person)

                outputStream.toString() shouldBe """
                    ${address.streetAddress}
                    ${address.zipCode} ${address.city}, ${address.country}
                    
                """.trimIndent()
            }
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!

        val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
        val jetbrains = Company("JetBrains", address)
        val person = Person("Dmitry", jetbrains)
    }
}
