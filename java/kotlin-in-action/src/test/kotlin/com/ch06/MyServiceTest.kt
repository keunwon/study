package com.ch06

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class MyServiceTest : DescribeSpec({
    beforeEach {
        myService = MyService()
    }

    describe("MyService") {
        it("문자열을 반환합니다") {
            myService.performAction() shouldBe "foo"
        }
    }
}) {
    companion object {
        lateinit var myService: MyService
    }
}
