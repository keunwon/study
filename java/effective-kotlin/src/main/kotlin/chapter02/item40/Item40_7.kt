package chapter02.item40

data class Date2(
    val year: Int,
    val month: Int,
    val day: Int
)

data class DateTime2(
    val date: Date2,
    val hour: Int,
    val minute: Int,
    val second: Int
)

fun main() {
    val o1 = DateTime2(Date2(1992, 10, 20), 12, 30, 0)
    val o2 = Date2(1992, 10, 20)
    val o3 = DateTime2(Date2(1992, 10, 20), 14, 45, 30)

    println(o1.equals(o2)) // false
    println(o2.equals(o3)) // false
    println(o1 == o3) // false

    println(o1.date.equals(o2)) // true
    println(o2.equals(o3.date)) // true
    println(o1.date == o3.date) // true
}
