package chapter02.item34

class Pizza2 private constructor(
    val size: String,
    val cheese: Int,
    val olives: Int,
    val bacon: Int
) {
    class Builder(private val size: String) {
        private var cheese = 0
        private var olives = 0
        private var bacon = 0

        fun setCheese(value: Int): Builder = apply {
            cheese = value
        }

        fun setOlives(value: Int): Builder = apply {
            olives = value
        }

        fun setBacon(value: Int): Builder = apply {
            bacon = value
        }

        fun build() = Pizza(size, cheese, olives, bacon)
    }
}

val villagePizza2 = Pizza(
    size = "L",
    cheese = 1,
    olives = 2,
    bacon = 3
)

fun main() {
    val myFavorite = Pizza2.Builder("L")
        .setCheese(1)
        .setOlives(2)
        .setBacon(3)
        .build()
}
