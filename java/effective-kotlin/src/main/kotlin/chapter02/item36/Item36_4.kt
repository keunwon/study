package chapter02.item36

class CounterSet<T> : HashSet<T>() {
    var elementsAdded = 0
        private set

    override fun add(element: T): Boolean {
        elementsAdded++
        return super.add(element)
    }
}

class CounterSet2<T>(
    private val innerSet: MutableList<T> = mutableListOf()
) : MutableList<T> by innerSet {
    var elementsAdded = 0
        private set

    override fun add(element: T): Boolean {
        elementsAdded++
        return innerSet.add(element)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        elementsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val counterList = CounterSet<String>()
    counterList.addAll(listOf("A", "B", "C"))
    println(counterList.elementsAdded)
}
