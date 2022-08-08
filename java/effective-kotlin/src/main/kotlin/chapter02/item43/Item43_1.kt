package chapter02.item43

open class C
class D : C()

fun C.foo() = "C"
fun D.foo() = "d"

fun main() {
    val d = D()
    println(d.foo()) //d

    val c: C = C()
    println(c.foo()) // c

    println(D().foo()) // d
    println((D() as C).foo()) // c
}
