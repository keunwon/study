package com.ch06

class Employee(val name: String, val manager: Employee?)

fun manageName(employee: Employee): String? = employee.manager?.name
