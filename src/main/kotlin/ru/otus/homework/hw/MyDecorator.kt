package ru.otus.homework.hw

fun main() {

    // базовый кофе
    val coffee: Coffee = SimpleCoffee()
    println("${coffee.getDescription()} за ${coffee.getCost()} $")


    val milkCoffee: Coffee = MilkDecorator(coffee)
    println("${milkCoffee.getDescription()} за ${milkCoffee.getCost()} $")


    val sugarMilkCoffee: Coffee = SugarDecorator(milkCoffee)
    println("${sugarMilkCoffee.getDescription()} за ${sugarMilkCoffee.getCost()} $")

}


interface Coffee {
    fun getCost(): Double
    fun getDescription(): String
}

class SimpleCoffee : Coffee {
    override fun getCost(): Double = 1.0
    override fun getDescription(): String = "Простой кофе"
}

abstract class CoffeeDecorator(private val decoratedCoffee: Coffee) : Coffee {
    override fun getCost(): Double = decoratedCoffee.getCost()
    override fun getDescription(): String = decoratedCoffee.getDescription()
}

class MilkDecorator(decoratedCoffee: Coffee) : CoffeeDecorator(decoratedCoffee) {
    override fun getCost(): Double = super.getCost() + 0.5
    override fun getDescription(): String = super.getDescription() + ", молоко"
}

class SugarDecorator(decoratedCoffee: Coffee) : CoffeeDecorator(decoratedCoffee) {
    override fun getCost(): Double = super.getCost() + 0.2
    override fun getDescription(): String = super.getDescription() + ", сахар"
}