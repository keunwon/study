package chapter03.item50

class Student(val name: String?)

fun List<Student>.getNames(): List<String> = this
    .map { it.name }
    .filter { it != null }
    .map { it!! }

fun List<Student>.getNames2(): List<String> = this
    .map { it.name }
    .filterNotNull()

fun List<Student>.getNames3(): List<String> = this
    .mapNotNull { it.name }

fun main() {
    val students = listOf(
        Student("김길동"),
        Student("나길동"),
        Student("다길동"),
        Student("라길동"),
    )

    println(students.getNames())
    println(students.getNames2())
    println(students.getNames3())
}
