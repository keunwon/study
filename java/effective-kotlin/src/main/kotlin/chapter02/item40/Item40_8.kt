package chapter02.item40

import java.net.URI
import java.net.URL

fun main() {
    val enWiki = URL("https://en.wikipedia.org/")
    val wiki = URL("https://wikipedia.org/")
    println(enWiki == wiki)

    val enWiki2 = URI("https://en.wikipedia.org/")
    val wiki2 = URI("https://wikipedia.org/")
    println(enWiki2 == wiki2)
}
