package chapter03.item47

typealias NewName = Int

typealias ClickListener = (view: View, event: Event) -> Unit

class Event

class View {
    fun addClickListener(listener: ClickListener) {}
    fun removeClickListener(listener: ClickListener) {}
}

fun main() {
    val n: NewName = 10
}
