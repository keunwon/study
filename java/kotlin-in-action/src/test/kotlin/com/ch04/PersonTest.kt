package com.ch04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly

internal class PersonTest : DescribeSpec({

    describe("Person") {

        context("이름을 기준으로 오름차순 정렬") {
            val persons = listOf(
                Person("홍길동"),
                Person("김길동"),
                Person("나길동")
            )

            persons.sortedWith(Person.NameComparator)
                .shouldContainExactly(listOf(
                    Person("김길동"),
                    Person("나길동"),
                    Person("홍길동")
                ))
        }
    }
})