package ru.otus.homework.patterns

fun main() {
    val coffee = Coffee()
    val coffeeWithMilk = Milk(coffee)
    val coffeeWithMilkAndSugar = Sugar(coffeeWithMilk)

    println("Простой кофе: ${coffee.cost()}")
    println("Кофе с молоком: ${coffeeWithMilk.cost()}")
    println("Кофе с молоком и сахаром: ${coffeeWithMilkAndSugar.cost()}")
}

interface Beverage {
    fun cost(): Double
}

class Coffee : Beverage {
    override fun cost(): Double = 5.0
}

class Milk(private val beverage: Beverage) : Beverage {
    override fun cost(): Double = beverage.cost() + 2.5
}

class Sugar(private val beverage: Beverage) : Beverage {
    override fun cost(): Double = beverage.cost() + 1.5
}
