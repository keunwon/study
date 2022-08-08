package chapter02.item38

class View

interface OnClick {
    fun clicked(view: View)
}

fun setOnClickListener(listener: OnClick) {
    listener.clicked(View())
}

fun setOnClickListener(listener: (View) -> Unit) {
    println("view")
    listener(View())
}

class ClickListener: (View) -> Unit {
    override fun invoke(p1: View) {
        println("invoke")
    }
}

typealias OnClick2 = (View) -> Unit
fun setOnClickListener2(listener: OnClick2) {
    println("setOnClickListener2")
    listener(View())
}

fun main() {
    setOnClickListener(object : OnClick {
        override fun clicked(view: View) {
            println("clicked")
        }
    })

    setOnClickListener { println("onClick") }
    setOnClickListener(::print)
    setOnClickListener(ClickListener())

    setOnClickListener2 { println("!!") }
}
