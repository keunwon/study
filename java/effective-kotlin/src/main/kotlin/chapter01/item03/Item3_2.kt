package chapter01.item03

open class Car
class Fiat126P : Car()

val DEFAULT_CAR = Fiat126P()

interface CarFactory {
    fun produce() = DEFAULT_CAR
}

class TestCart : CarFactory {
    override fun produce(): Fiat126P {
        // return Car() error
        return Fiat126P()
    }
}
