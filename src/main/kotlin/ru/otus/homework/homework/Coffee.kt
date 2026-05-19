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

class SimpleCoffee : Coffee {
    override fun cost() = 200
    override fun description() = "Простой кофе"
}

abstract class CoffeeDecorator(
    private val coffee: Coffee,
    private val ingredientCost: Int,
    private val ingredientDescription: String,
) : Coffee {
    override fun cost(): Int = coffee.cost() + ingredientCost

    override fun description(): String = "${coffee.description()}, $ingredientDescription"
}

class MilkDecorator(coffee: Coffee) : CoffeeDecorator(
    coffee = coffee,
    ingredientCost = 50,
    ingredientDescription = "молоко",
)

class SugarDecorator(coffee: Coffee) : CoffeeDecorator(
    coffee = coffee,
    ingredientCost = 20,
    ingredientDescription = "сахар",
)

class VanillaDecorator(coffee: Coffee) : CoffeeDecorator(
    coffee = coffee,
    ingredientCost = 70,
    ingredientDescription = "ваниль",
)