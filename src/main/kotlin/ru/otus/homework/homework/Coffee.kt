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

/**
 * Базовый декоратор: хранит оборачиваемый напиток и знает,
 * как добавить к нему стоимость и название ингредиента.
 * Наследникам остается только объявить свою добавку.
 */
abstract class CoffeeDecorator(private val coffee: Coffee) : Coffee {
    /**
     * Стоимость добавки в копейках
     */
    protected abstract val additionalCost: Int

    /**
     * Название добавки для описания напитка
     */
    protected abstract val ingredient: String

    override fun cost(): Int = coffee.cost() + additionalCost

    override fun description(): String = "${coffee.description()}, $ingredient"
}

class MilkDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override val additionalCost = 50
    override val ingredient = "молоко"
}

class SugarDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override val additionalCost = 20
    override val ingredient = "сахар"
}

class VanillaDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override val additionalCost = 70
    override val ingredient = "ваниль"
}
