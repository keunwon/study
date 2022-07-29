package chapter01.item02

fun updateWeather(degrees: Int) {
    val description: String
    val color: Int

    if (degrees < 5) {
        description = "cold"
        color = 1
    } else if (degrees < 23) {
        description = "mid"
        color = 2
    } else {
        description = "hot"
        color = 3
    }
    println("$description, $color")
}

fun updateWeather2(degrees: Int) {
    val (description, color) = when {
        degrees < 5 -> "cold" to 1
        degrees < 23 -> "mid" to 2
        else -> "hot" to 3
    }
    println("$description, $color")
}

fun updateWeather3(degrees: Int): Pair<String, Int> {
    return when {
        degrees < 5 -> "cold" to 1
        degrees < 23 -> "mid" to 2
        else -> "hot" to 3
    }
}

fun main() {
    updateWeather(4)
    updateWeather(22)
    updateWeather(25)

    updateWeather2(4)
    updateWeather2(22)
    updateWeather2(25)

    println(updateWeather3(4))
    println(updateWeather3(23))
    println(updateWeather3(25))
}
