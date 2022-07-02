package com.ch08

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSingleElement

internal class ContactListFiltersTest : DescribeSpec({

    describe("getPredicate") {
        it("onlyWithPhoneNumber true 인 경우") {
            with (contactListFilters) {
                prefix = "Dm"
                onlyWithPhoneNumber = true
            }

            val list = contacts.filter(contactListFilters.getPredicate())

            list shouldHaveSingleElement contacts[0]
        }

        it("onlyWithPhoneNumber false 인 경우") {
            with (contactListFilters) {
                prefix = "Dm"
                onlyWithPhoneNumber = false
            }

            val list = contacts.filter(contactListFilters.getPredicate())

            list shouldHaveSingleElement contacts[0]
        }
    }
}) {
    companion object {
        val contacts = listOf(
            Person("Dmitry", "Jemerov", "123-4567"),
            Person("Svetlana", "Isakova", null)
        )
        val contactListFilters = ContactListFilters()
    }
}
