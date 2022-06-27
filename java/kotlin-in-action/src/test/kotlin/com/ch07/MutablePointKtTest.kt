package com.ch07

import io.kotest.assertions.throwables.shouldThrowExactlyUnit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class MutablePointKtTest : DescribeSpec({

    describe("set") {
        context("[0 ~ 1] 범위 이외의 인덱스 값을 입력받으면") {
            it("IndexOutOfBoundsException 발생합니다") {
                val mutablePoint = MutablePoint(10, 10)

                listOf(-1, 2, 3, 4, 5).forAll { num ->
                    shouldThrowExactlyUnit<IndexOutOfBoundsException> {
                        mutablePoint[num] = num
                    }.message shouldBe "Invalid coordinate $num"
                }
            }
        }

        it("인덱스가 0인 경우 x의 값을 변경합니다") {
            listOf(20, 30, 40, 50).forAll { num ->
                val mutablePoint = MutablePoint(10, 10)
                mutablePoint[0] = num

                mutablePoint shouldBe MutablePoint(num, mutablePoint.y)
            }
        }

        it("인덱스가 1인 경우 y의 값을 변경합니다") {
            listOf(20, 30, 40, 50).forAll { num ->
                val mutablePoint = MutablePoint(10, 10)
                mutablePoint[1] = num

                mutablePoint shouldBe MutablePoint(mutablePoint.x, num)
            }
        }
    }
})
