package chapter02.item40

class Complex(
    val real: Double,
    val imaginary: Double
) {
    override fun equals(other: Any?): Boolean {
        if (other is Double) {
            return imaginary == 0.0 && real == other
        }
        return other is Complex &&
                real == other.real &&
                imaginary == other.imaginary
    }
}

fun main() {
    println(Complex(1.0, 0.0).equals(1.0)) // true
    println(1.0.equals(Complex(1.0, 0.0))) // false
}
