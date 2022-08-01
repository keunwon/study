package chapter02.item23

interface Tree
class Birch : Tree
class Spruce : Tree

class Forest<T: Tree> {
    fun <T: Tree> addTree(tree: T) {
        println(tree.javaClass.kotlin.simpleName)
    }
}

class Forest2<T: Tree> {
    fun addTree(tree: T) {
        println(tree.javaClass.kotlin.simpleName)
    }
}

class Forest3<T: Tree> {
    fun <ST: Tree> addTree(tree: ST) {
        println(tree.javaClass.kotlin.simpleName)
    }
}

fun main() {
    println("----- forest1 -----")
    val forest = Forest<Birch>()
    forest.addTree(Birch())
    forest.addTree(Spruce())

    println("----- forest2 -----")
    val forest2 = Forest2<Birch>()
    forest2.addTree(Birch())
    //forest2.addTree(Spruce())

    println("----- forest3 -----")
    val forest3 = Forest3<Birch>()
    forest3.addTree(Spruce())
    forest3.addTree(Birch())
}
