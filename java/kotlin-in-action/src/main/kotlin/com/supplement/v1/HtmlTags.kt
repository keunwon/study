package com.supplement.v1

@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
abstract class Tag(val name: String) {
    private val children = mutableListOf<Tag>()

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }

    override fun toString(): String {
        return "<$name>${children.joinToString()}</$name>"
    }
}

class Table : Tag("table") {
    fun tr(init: Tr.() -> Unit) = doInit(Tr(), init)
}

class Tr : Tag("tr") {
    fun td(init: Td.() -> Unit) = doInit(Td(), init)
}

class Td : Tag("td")


fun table(init: Table.() -> Unit) = Table().apply(init)

fun createHtml() = table {
    tr {
        td {

        }
    }
}
