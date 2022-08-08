package chapter02.item42

class User(val name: String, val surname: String) {

    companion object {
        val DISPLAY_ORDER = compareBy(User::surname, User::name)
    }
}

class User2(val name: String, val surname: String) : Comparable<User2> {
    override fun compareTo(other: User2): Int {
        return compareValues(surname, other.surname)
    }
}

class User3(val name: String, val surname: String) : Comparable<User3> {
    override fun compareTo(other: User3): Int {
        return compareValuesBy(this, other, { it.surname }, { it.name} )
    }
}

fun main() {
    val names = listOf(
        User("길동-1", "라"),
        User("길동-1", "다"),
        User("길동-1", "가"),
        User("길동-2", "다"),
        User("길동-2", "가"),
        User("길동-1", "나"),
        User("길동-2", "나"),
        User("길동-2", "라"),
    )

    val sortedName1 = names.sortedBy { it.surname }
    val sortedName2 = names.sortedWith(compareBy({ it.surname }, { it.name }))
    val sortedName3 = names.sortedWith(User.DISPLAY_ORDER)

    println(namesJoinToString(sortedName1))
    println(namesJoinToString(sortedName2))
    println(namesJoinToString(sortedName3))

    println("----- compareValues -----")
    val names2 = names.map { User2(it.name, it.surname) }
    println(names2.sorted().joinToString { it.surname + it.name })

    println("----- compareValuesBy -----")
    val names3 = names.map { User3(it.name, it.surname) }
    println(names3.sorted().joinToString { it.surname + it.name })
}

fun namesJoinToString(list: List<User>): String =
    list.joinToString { it.surname + it.name }
