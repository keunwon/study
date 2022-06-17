package com.ch04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly

internal class PersonTest : DescribeSpec({

    describe("Person") {
        it("이름을 기준으로 오름차순 정렬") {
            persons.sortedWith(Person.NameComparator).shouldContainExactly(
                Person("김길동"), Person("나길동"), Person("홍길동")
            )
        }
    }
}) {
    companion object {
        val persons = listOf(
            Person("홍길동"),
            Person("김길동"),
            Person("나길동")
        )
    }
}
