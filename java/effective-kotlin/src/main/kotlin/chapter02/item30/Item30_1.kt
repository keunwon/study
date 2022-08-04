package chapter02.item30

class CounterSet<T>(
    private val innerSet: MutableSet<T> = mutableSetOf()
) : MutableSet<T> by innerSet {

    var elementsAdded: Int = 0
        private set

    override fun add(element: T): Boolean {
        elementsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        elementsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val counterSet = CounterSet<String>()
    println("${counterSet.add("apple")}")
    println("${counterSet.elementsAdded}")

    println(counterSet.addAll(listOf("banana", "cat")))
    println("${counterSet.elementsAdded}")
}
