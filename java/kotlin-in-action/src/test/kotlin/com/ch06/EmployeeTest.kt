package com.ch06

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

internal class EmployeeTest : DescribeSpec({

    describe("manageName") {
        it("매니저 이름을 출력합니다") {
            manageName(developer) shouldBe ceo.name
            manageName(ceo).shouldBeNull()
        }
    }
}) {
    companion object {
        val ceo = Employee("Da Boss", null)
        val developer = Employee("Bob Smith", ceo)
    }
}
