package chapter02.item40

open class Animal
class Book
class Car : Animal()

fun main() {
    //println(Animal() == Book()) error
    //println(Animal() === Book()) error

    println(Animal() == Car())
    println(Animal() === Car())
}
