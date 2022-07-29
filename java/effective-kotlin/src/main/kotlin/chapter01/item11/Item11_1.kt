package chapter01.item11

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.zip.ZipInputStream

class Person(val name: String)
var person: Person? = null

fun printName() {
    person?.let { println(it.name) }
}

var obj = FileInputStream("file.gz")
    .let(::BufferedInputStream)
    .let(::ZipInputStream)
    .let(::ObjectInputStream)
    .readObject()

fun main() {
    printName()
}
