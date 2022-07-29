package chapter01.item04

class Person(val email: String?)
fun validateEmail(email: String) {}

fun sendEmail(person: Person, text: String) {
    val email = requireNotNull(person.email)
    validateEmail(email)
    println(email)
}

fun sendEmail2(person: Person, text: String) {
    requireNotNull(person.email)
    validateEmail(person.email)
    println(person.email)
}

fun sendEmail3(person: Person, text: String) {
    val email: String = person.email ?: return
    println(email)
}

fun sendEmail4(person: Person, text: String) {
    val email = person.email ?: run {
        println("Email not sent, no email address")
        return
    }
    println(email)
}

fun main() {
    val arguments = Person("test@test.com") to "text"
    sendEmail(arguments.first, arguments.second)
    sendEmail2(arguments.first, arguments.second)

    sendEmail3(Person(null), "")
    sendEmail4(Person(null), "")
}
