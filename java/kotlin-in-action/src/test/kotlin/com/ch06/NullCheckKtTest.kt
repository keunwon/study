package com.ch06

import com.ch06.java.Person
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class NullCheckKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("strLen") {
        it("입력된 문자열의 길이를 반환합니다") {
            listOf(
                "일" to 1,
                "둘둘" to 2,
                "셋셋셋" to 3,
                "넷넷넷넷" to 4
            ).forAll { (text, len) ->
                strLen(text) shouldBe len
            }
        }
    }

    describe("strLenSafe") {
        context("문자열을 입력하면") {
            it("입력된 문자열의 길이를 반환합니다") {
                listOf(
                    "일" to 1,
                    "둘둘" to 2,
                    "셋셋셋" to 3,
                    "넷넷넷넷" to 4
                ).forAll { (text, len) ->
                    strLen(text) shouldBe len
                }
            }
        }

        context("null 타입으로 입력되는 경우") {
            it("0으로 반환합니다") {
                strLenSafe(null) shouldBe 0
            }
        }
    }

    describe("printAllCaps") {
        it("null 타입으로 입력되면 null 출력합니다") {
            printAllCaps(null)

            outputStream.toString() shouldBe "null\n"
        }

        it("소문자 문자열을 입력하면 대문자로 변경 후 출력합니다") {
            printAllCaps("apple")

            outputStream.toString() shouldBe "APPLE\n"
        }
    }

    describe("ignoreNulls") {
        it("null 타입을 입력하면 NullPointerException 발생합니다") {
            shouldThrowExactly<NullPointerException> {
                ignoreNulls(null)
            }
        }

        it("입력한 문자열의 길이를 반환합니다") {
            ignoreNulls("세글자")
            outputStream.toString() shouldBe "3\n"
        }
    }

    describe("sendEmailTo") {
        it("문자열을 입력하면, 안내 메시지를 출력합니다") {
            val email = "test@google.com"

            sendEmailTo(email)

            outputStream.toString() shouldBe "Sending email to $email"
        }

        it("null 입력된 경우 아무것도 출력되지 않습니다") {
            val email: String? = null

            email?.let { sendEmailTo(it) }

        }
    }

    describe("verifyUserInput") {
        it("null 타입 입력 시 안내 메시지를 출력합니다") {
            verifyUserInput(null)

            outputStream.toString() shouldBe "Please fill in the required fields\n"
        }

        it("문자열 공백만 입력 시 안내 메시지를 출력합니다") {
            verifyUserInput(" ")

            outputStream.toString() shouldBe "Please fill in the required fields\n"
        }
    }

    describe("yellAt") {
        it("person.name null, 'ANYONE!!!!' 출력합니다") {
            val person = Person(null)
            yellAt(person)

            outputStream.toString() shouldBe "ANYONE!!!\n"
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}
