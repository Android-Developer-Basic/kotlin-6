package ru.otus.homework.homework

/**
 * Базовый интерфейс кофейного напитка
 */
interface Coffee {
    /**
     * Цена кофейного напитка в копейках
     */
    fun cost(): Int

    /**
     * Описание кофейного напитка
     */
    fun description(): String
}

sealed class CoffeeIngredient : Coffee {
    class Milk : CoffeeIngredient() {
        override fun cost(): Int = 50
        override fun description(): String = "молоко"
    }

    class Sugar : CoffeeIngredient() {
        override fun cost(): Int = 20
        override fun description(): String = "сахар"
    }

    class Vanilla : CoffeeIngredient() {
        override fun cost(): Int = 70
        override fun description(): String = "ваниль"
    }
}

class CoffeeDecorator(private val coffee: Coffee, private val ingredient: Coffee) : Coffee {
    override fun cost(): Int = coffee.cost() + ingredient.cost()

    override fun description(): String = "${coffee.description()}, ${ingredient.description()}"
}

class SimpleCoffee : Coffee {
    override fun cost() = 200
    override fun description() = "Простой кофе"
}

class MilkDecorator(private val coffee: Coffee) :
    Coffee by CoffeeDecorator(coffee, CoffeeIngredient.Milk())

class SugarDecorator(private val coffee: Coffee) :
    Coffee by CoffeeDecorator(coffee, CoffeeIngredient.Sugar())

class VanillaDecorator(private val coffee: Coffee) :
    Coffee by CoffeeDecorator(coffee, CoffeeIngredient.Vanilla())
