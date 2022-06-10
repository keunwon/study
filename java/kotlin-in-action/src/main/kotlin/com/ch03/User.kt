package com.ch03

class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave() {
    fun validate(value: String, field: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${id}: empty $field")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser(user: User) {
    user.validateBeforeSave()
}