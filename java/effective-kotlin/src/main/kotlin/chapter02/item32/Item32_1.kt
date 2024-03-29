package chapter02.item32

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

class Employee {
    private val id = 2

    override fun toString(): String = "User(id=$id)"

    private fun privateFunction() = println("Private function called")
}

fun callPrivateFunction(employee: Employee) {
    employee::class.declaredFunctions
        .first { it.name == "privateFunction" }
        .apply { isAccessible = true }
        .call(employee)
}

fun changeEmployeeId(employee: Employee, newId: Int) {
    employee::class.java.getDeclaredField("id")
        .apply { isAccessible = true }
        .set(employee, newId)
}

fun main() {
    val employee = Employee()
    callPrivateFunction(employee)
    changeEmployeeId(employee, 1)
    println(employee)
}
