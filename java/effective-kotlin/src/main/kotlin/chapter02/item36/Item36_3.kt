package chapter02.item36

abstract class Dog {
    open fun bark() {}
    open fun sniff() {}
}

class Labrador : Dog()

class RobotDog : Dog() {
    override fun sniff() {
        throw Error("Operation not supported")
    }
}
