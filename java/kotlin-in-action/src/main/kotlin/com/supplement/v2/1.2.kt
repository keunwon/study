package com.supplement.v2

@Target(AnnotationTarget.FUNCTION)
annotation class RequestMapping(val value: Array<String>)

@RequestMapping(value = ["v1", "v2"])
fun request() {}

class Person(url: String) {
    lateinit var _url: String

    init {
        println(isInit())
        _url = url
    }

    fun isInit(): Boolean {
        return ::_url.isInitialized
    }
}

inline fun <E> Iterable<E>.strings(transform: (E) -> String = { it.toString() }) = map { transform(it) }
val defaultStrings = listOf(1, 2, 3).strings()
val customStrings = listOf(1, 2, 3).strings { (it + 10).toString() }

fun main() {
    println("----- late init -----")
    val p = Person("http://naver.com")
    println(p._url)
    println(p.isInit())

    println("----- transform -----")
    println(defaultStrings)
    println(customStrings)
}
