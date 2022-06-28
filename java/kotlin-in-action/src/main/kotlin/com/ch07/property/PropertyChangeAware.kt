package com.ch07.property

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Person(
    val name: String,
    age: Int,
    salary: Int
) : PropertyChangeAware() {
    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)

    override fun toString(): String {
        return "Person(name='$name', age=$age, salary=$salary)"
    }
}

class Person2 {
    private val _attribute = hashMapOf<String, String>()

    val name: String by _attribute

    fun setAttribute(attrName: String, value: String) {
        _attribute[attrName] = value
    }
}

class ObservableProperty(
    var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(p: Person, prop: KProperty<*>): Int = propValue

    operator fun setValue(p: Person, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

fun main() {
    val p = Person("Dmitry", 34, 2000)
    p.addPropertyChangeListener {
        println("Property ${it.propertyName} changed from ${it.oldValue} to ${it.newValue}")
    }

    p.age = 35
    p.salary = 2100
    println(p)

    println("======================================")
    val p2 = Person2()
    p2.setAttribute("name", "홍길동")
    println(p2.name)
}
