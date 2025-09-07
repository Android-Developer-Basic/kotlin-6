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

abstract class CoffeeDecorator(private val coffee: Coffee) : Coffee {
    protected val base: Coffee = coffee
    override fun cost() = base.cost()
    override fun description() = base.description()
}

class MilkDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun cost(): Int = base.cost() + 50
    override fun description(): String = base.description() + ", молоко"
}

class SugarDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun cost(): Int = base.cost() + 20
    override fun description(): String = base.description() + ", сахар"
}

class VanillaDecorator(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun cost(): Int = base.cost() + 70
    override fun description(): String = base.description() + ", ваниль"
}