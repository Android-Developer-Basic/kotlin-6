package ru.otus.homework.decorator_learning

abstract class PizzaDecorator(private val basicPizza: BasicPizza) : BasicPizza {
    override fun createPizza(): String  = basicPizza.createPizza()
}