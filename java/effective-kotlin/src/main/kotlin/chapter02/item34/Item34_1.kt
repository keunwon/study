package chapter02.item34

class Pizza(
    val size: String,
    val cheese: Int = 0,
    val olives: Int = 0,
    val bacon: Int = 0
)

val villagePizza = Pizza(
    size = "L",
    cheese =  1,
    olives = 2,
    bacon = 3
)

fun main() {
    val myFavorite = Pizza("L", olives = 3)
    val myFavorite2 = Pizza("L", olives = 3, cheese = 1)
}
