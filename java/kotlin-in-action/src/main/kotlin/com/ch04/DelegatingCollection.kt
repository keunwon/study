package com.ch04

class DelegatingCollection<T>(
    val innerList: Collection<T> = ArrayList()
) : Collection<T> by innerList {

}

class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet()
) : MutableCollection<T> by innerSet {

    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }
}