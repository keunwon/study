package com.ch08

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import kotlin.math.round

internal class DeliveryTest : DescribeSpec({

    describe("getShippingCostCalculator") {
        it("STANDARD 입력하면 계산한 값을 반환합니다") {
            val doubleNum = getShippingCostCalculator(Delivery.STANDARD).invoke(Order(3))

            roundNum(doubleNum) shouldBe 3.6
        }

        it("EXPEDITED 입력하면 계싼한 값을 반환합니다") {
            val doubleNum = getShippingCostCalculator(Delivery.EXPEDITED).invoke(Order(3))
            roundNum(doubleNum) shouldBe 12.3
        }
    }
}) {
    companion object {
        fun roundNum(num: Double) : Double {
            return round(num * 100) / 100
        }
    }
}
