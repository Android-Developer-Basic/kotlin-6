package ru.otus.homework

interface Coffee {
    fun cost(): Double
    fun description(): String
}

class SimpleCoffee : Coffee {
    override fun cost() = 1.0
    override fun description() = "Simple coffee"
}

abstract class CoffeeDecorator(private val decoratedCoffee: Coffee) : Coffee {
    override fun cost() = decoratedCoffee.cost()
    override fun description() = decoratedCoffee.description()
}

class MilkDecorator(decoratedCoffee: Coffee) : CoffeeDecorator(decoratedCoffee) {
    override fun cost() = super.cost() + 0.5
    override fun description() = super.description() + ", milk"
}

class SugarDecorator(decoratedCoffee: Coffee) : CoffeeDecorator(decoratedCoffee) {
    override fun cost() = super.cost() + 0.2
    override fun description() = super.description() + ", sugar"
}


fun main() {
    val simpleCoffee = SimpleCoffee()
    println("${simpleCoffee.description()}: ${simpleCoffee.cost()}")

    val milkCoffee = MilkDecorator(simpleCoffee)
    println("${milkCoffee.description()}: ${milkCoffee.cost()}")

    val milkCoffeeWithSugar = SugarDecorator(milkCoffee)
    println("${milkCoffeeWithSugar.description()}: ${milkCoffeeWithSugar.cost()}")
}