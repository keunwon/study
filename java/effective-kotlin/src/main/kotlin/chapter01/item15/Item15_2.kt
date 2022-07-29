package chapter01.item15

class Node(val name: String) {

    fun makeChild(childName: String) =
        create("$name.$childName")
            .apply { println("Created ${this?.name}") }

    fun makeChild2(childName: String) =
        create("$name.$childName")
            .also { println("Created ${it?.name}") }

    fun makeChild3(childName: String) =
        create("$name.$childName").apply {
            println("Created ${this?.name} in ${this@Node.name}")
        }

    fun create(name: String): Node? = Node(name)
}

fun main() {
    val node = Node("parent")
    node.makeChild("child")
    node.makeChild2("child2")
    node.makeChild3("child3")
}
