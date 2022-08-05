package chapter02.item33

data class Student(
    val id: Int,
    val name: String,
    val surname: String
)

class StudentFactory {
    var nextId = 0

    fun next(name: String, surname: String) =
            Student(nextId++, name, surname)
}

fun main() {
    val factory = StudentFactory()
    val s1 = factory.next("Marcin", "Moskala")
    println(s1)
    val s2 = factory.next("Igor", "Wojda")
    print(s2)
}
