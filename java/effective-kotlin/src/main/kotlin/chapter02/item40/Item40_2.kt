package chapter02.item40

class Name(val name: String)

fun main() {
    val name1 = Name("Marcin")
    val name2 = Name("Marcin")
    val nameRef = name1

    println(name1 == name1) // true
    println(name1 == name2) // false
    println(name1 == nameRef) // true
    println(name1 === name1) // true
    println(name1 === name2) // false
    print(name1 === nameRef) // true
}
