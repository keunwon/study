package chapter02.item37

data class FullName(
    val firstName: String,
    val secondName: String,
    val lastName: String
)

data class FullName2(
    val firstName: String,
    val lstName: String
)

fun String.parseName(): FullName2? {
    val indexOfLastSpace = this.trim().lastIndexOf(' ')
    if (indexOfLastSpace < 0) return null
    val firstName = this.take(indexOfLastSpace)
    val lastName = this.drop(indexOfLastSpace)
    return FullName2(firstName, lastName)
}

fun main() {
    val elon = FullName("Elon", "Reeve","Mush")
    val (name, surname) = elon
    println("It is $name $surname")

    val fullName = "Marcin Moskala"
    val (firstName, lastName) = fullName.parseName() ?: return
    println("$firstName, $lastName")
}
