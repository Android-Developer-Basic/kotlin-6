package ru.otus.homework

interface Coffee {
    fun cost(): Double
    fun getDescription(): String
}

class Espresso : Coffee {
    override fun cost(): Double = 1.5
    override fun getDescription(): String = "Эспрессо"
}

abstract class CoffeeDecorator(val coffee: Coffee) : Coffee by coffee

class MilkDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun cost(): Double = coffee.cost() + 0.5
    override fun getDescription(): String = coffee.getDescription() + ", с молоком"
}

class SugarDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun cost(): Double = coffee.cost() + 0.2
    override fun getDescription(): String = coffee.getDescription() + ", с сахаром"
}

fun main() {
    val espresso = Espresso()
    val milkEspresso = MilkDecorator(espresso)
    val milkSugarEspresso = SugarDecorator(milkEspresso)

    println("Цена: ${milkEspresso.cost()}, Описание: ${milkEspresso.getDescription()}")
    println("Цена: ${milkSugarEspresso.cost()}, Описание: ${milkSugarEspresso.getDescription()}")
}
