package ru.otus.homework.patterns

fun main() {
    createCar()
}

fun createCar() {
    val poloCar = PoloCar()
    println(poloCar)

    val decoratedPolo = RedCollor(poloCar).decorate()
    println(decoratedPolo)
}

interface Car {
    fun decorate(): String
}

class PoloCar() : Car {
    override fun decorate(): String {
        return "polo"
    }
}

class RedCollor(private val car: Car) : Car by car {
    override fun decorate(): String {
        return car.decorate() + paintRed()
    }

    private fun paintRed(): String {
        return "Red"
    }
}

