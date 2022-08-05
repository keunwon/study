package chapter02.item36

open class Parent {
    fun a() {}
    open fun b() {}
}

open class Child : Parent() {
    final override fun b() {
        super.b()
    }
}
