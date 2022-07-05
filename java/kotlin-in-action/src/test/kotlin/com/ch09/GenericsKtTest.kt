package com.ch09

import io.kotest.assertions.throwables.shouldThrowExactlyUnit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.shouldBe
import java.security.Provider

internal class GenericsKtTest : DescribeSpec({

    describe("letters") {
        it("[a ~ z] 범위의 리스트를 반환합니다") {
            letters
                .shouldHaveSize(26)
                .shouldContainExactly(
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
        }

        it("[a ~ c] 범위의 리스트를 반환합니다") {
            val list = letters.slice(0..2)

            list.shouldHaveSize(3)
                .shouldContainExactly('a', 'b', 'c')
        }

        it("[k ~ n] 범위의 리스트를 반환합니다") {
            val list = letters.slice(10..13)

            list.shouldHaveSize(4)
                .shouldContainExactly('k', 'l', 'm', 'n')
        }
    }

    describe("penultimate") {
        it("리스트의 마지막에서 2번째 인덱스의 값을 반환합니다") {
            val strings = listOf("apple", "banana", "cat", "dog", "egg")

            strings.penultimate shouldBe strings.reversed()[1]
        }
    }

    describe("oneHalf") {
        it("숫자형 타입을 입력받으면 2.0으로 나눈 double 타입의 결과를 반환합니다") {
            listOf(4 to 2.0, 6 to 3.0, 3 to 1.5).forAll { (num, mod) ->
                oneHalf(num) shouldBeExactly mod
            }
        }
    }

    describe("max") {
        it("오름차순으로 비교하여 문자열을 반환합니다") {
            max("kotlin", "java") shouldBe "kotlin"
        }
    }

    describe("ensureTrailingPeriod") {
        it("문자열이 '.'으로 끝나지 않으면 '.'을 붙입니다") {
            val helloWorld = StringBuilder("Hello World")

            ensureTrailingPeriod(helloWorld)

            helloWorld.toString() shouldBe "Hello World."
        }
    }

    describe("printSum") {
        it("set 타입을 입력하면 IllegalArgumentException 발생합니다") {
            shouldThrowExactlyUnit<IllegalArgumentException> {
                printSum(setOf(1, 2, 3))
            }.message shouldBe "List is expected"
        }

        it("문자열 리스트를 입력하면 ClassCastException 발생합니다") {
            shouldThrowExactlyUnit<ClassCastException> {
                printSum(listOf("a", "b", "c"))
            }
        }
    }

    describe("isA") {
        it("제너릭 타입과 입력된 값의 타입이 같으면 true 반환합니다") {
            isA<Int>(1).shouldBeTrue()
            isA<String>("1").shouldBeTrue()
            isA<Char>('1').shouldBeTrue()
            isA<Double>(1.1).shouldBeTrue()
        }

        it("제너릭 타입과 입력된 값의 타입이 다르면 false 반환합니다") {
            isA<Int>('1').shouldBeFalse()
            isA<String>(1).shouldBeFalse()
            isA<Double>(1).shouldBeFalse()
        }
    }

    describe("filterIsInstance") {
        context("서로 다른 타입을 포함하고 있는 리스트를 입력하는 경우") {
            it("문자열 타입만 리스트로 반환합니다") {
                anyList.filterIsInstance<String>() shouldContainExactly listOf("apple", "banana")
            }

            it("숫자형 타입만 리스트로 반환합니다") {
                anyList.filterIsInstance<Int>() shouldContainExactly listOf(10000, 20000)
            }

            it("문자 타입만 리스트로 반환합니다") {
                anyList.filterIsInstance<Char>() shouldContainExactly listOf('a')
            }

            it("실수형 타입만 리스트로 반환합니다") {
                anyList.filterIsInstance<Double>() shouldContainExactly listOf(1.2, 3.14)
            }
        }
    }

    xdescribe("loadService") {
        it("컴파일 오류가 발생하지 않습니다") {
            loadService<Provider.Service>()
        }
    }
}) {
    companion object {
        val anyList = listOf("apple", 10000, 'a', "banana", 1.2, null, 20000, 3.14)
    }
}
