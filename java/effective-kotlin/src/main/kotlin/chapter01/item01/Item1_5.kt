package chapter01.item01

import kotlin.concurrent.thread
import kotlin.properties.Delegates


private fun testList() {
    val list1: MutableList<Int> = mutableListOf()
    var list2: List<Int> = listOf(1)

    list1.add(1)
    list2 += 2
    println(list2)
}

private fun testThread() {
    var list = listOf<Int>()
    for (i in 1..1000) {
        thread {
            list = list + 1
        }
    }
    Thread.sleep(1000)
    println(list.size)
}

private var names by Delegates.observable(listOf<String>()) { _, old, new ->
    println("Names changed from $old to $new")
}

var announcements = listOf<Announcement>()
    private set

class Announcement

var list3 = mutableListOf<Int>()

fun main() {
    testList()
    testThread()

    names += "Fabio"
    names += "Bill"

    list3 += 1
    println(list3)
}
