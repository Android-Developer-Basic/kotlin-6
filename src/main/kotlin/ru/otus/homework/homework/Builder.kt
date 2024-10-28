package ru.otus.homework.homework

interface Level

class Roof : Level {
    init {
        println("Крыша построена")
    }

    override fun toString(): String {
        return "Крыша"
    }


}

class Basement: Level {

    init {
        println("Подвал построен")
    }

    override fun toString(): String {
        return "Подвал"
    }


}

class Storey: Level {
    init {
        println("Этаж построен")
    }

    override fun toString(): String {
        return "Этаж"
    }


}

class House {
    private val parts = mutableListOf<Level>()

    fun add(level: Level) {
        parts.add(level)
    }

    override fun toString(): String {
        return "House(parts=$parts)"
    }

}

abstract class Builder {
    abstract fun buildBasement()
    abstract fun buildStorey()
    abstract fun buildRoof()
    abstract fun getResult(): House
}

class ConcreteBuilder : Builder() {

    private val house = House()
    override fun buildBasement() {
        house.add(Basement())
    }

    override fun buildStorey() {
        house.add(Storey())
    }

    override fun buildRoof() {
        house.add(Roof())
    }

    override fun getResult(): House {
        return house
    }

}

fun main() {
    val concreteBuilder = ConcreteBuilder()
    concreteBuilder.buildBasement()
    concreteBuilder.buildStorey()
    concreteBuilder.buildStorey()
    concreteBuilder.buildRoof()
    println(concreteBuilder.getResult())
}