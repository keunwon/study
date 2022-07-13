package com.ch11

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class ImportantIssuePredicateTest : DescribeSpec({

    describe("ImportantIssuePredicate") {
        it("project 이름이 다른 경우 false 반환합니다") {
            val predicate = ImportantIssuePredicate("none")

            predicate(issue) shouldBe false
        }

        it("type[=Bug]이면 false 반환합니다") {
            val predicate = ImportantIssuePredicate(issue.project)
            val copyIssue = issue.copy(type = "Bug")

            predicate(copyIssue) shouldBe false
        }

        it("priority 특정 값이 존재하면 false 반환합니다") {
            listOf("Major", "Critical").forAll { priority ->
                val predicate = ImportantIssuePredicate(issue.project)
                val copyIssue = issue.copy(priority = priority)

                predicate(copyIssue) shouldBe false
            }
        }
    }
}) {
    companion object {
        val issue = Issue(
            id = "IDEA-154446",
            project = "IDEA",
            type = "Feature",
            priority = "Normal",
            description = ""
        )
    }
}
