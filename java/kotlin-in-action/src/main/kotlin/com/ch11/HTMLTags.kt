package com.ch11

import kotlinx.html.UL
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.li
import kotlinx.html.role
import kotlinx.html.span
import kotlinx.html.stream.createHTML
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.tr
import kotlinx.html.ul

open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }

    override fun toString(): String {
        return "<$name>${children.joinToString("")}</$name>"
    }
}

class Table : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td") {}

fun table(init: Table.() -> Unit) = Table().apply(init)

fun createTable() = table {
    tr {
        td {  }
        td {  }
    }
    tr {
        td {  }
        td {  }
        td {  }
    }
}

fun createAnotherTable() = table{
    for (i in 1..2) {
        tr {
            td {

            }
        }
    }
}

fun createAnotherTable2() = createHTML().table {
    val numbers = mapOf(1 to "one", 2 to "two")

    for ((num, string) in numbers) {
        tr {
            td { +"$num" }
            td { +string }
        }
    }
}

fun createSimpleTable() = createHTML().table {
    tr {
        td { +"cell" }
    }
}

fun buildDropdown() = createHTML().div(classes = "dropdown") {
    button(classes = "btn dropdown-toggle") {
        +"Dropdown"
        span(classes = "caret")
    }
    ul(classes = "dropdown-menu") {
        li { a(href = "#") { +"Action" } }
        li { a(href = "#") { +"Another action" } }
        li { role = "separator"; classes = setOf("divider") }
        li { classes = setOf("dropdown-header"); +"Header" }
        li { a(href = "#") { +"Separated link" } }
    }
}

fun UL.divider() = li { role = "separator"; classes = setOf("divider") }
fun UL.dropdownHeader(text: String) =
    li { classes = setOf("dropdown-header"); +text }

