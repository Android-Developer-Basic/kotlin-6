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

class MilkDecorator(private val coffee: Coffee) : Coffee {
    override fun cost(): Int {
        return coffee.cost() + 50
    }

    override fun description(): String {
        return coffee.description() + ", молоко"
    }
}

class SugarDecorator(private val coffee: Coffee) : Coffee {
    override fun cost(): Int {
        return coffee.cost() + 20
    }

    override fun description(): String {
        return coffee.description() + ", сахар"
    }
}

class VanillaDecorator(private val coffee: Coffee) : Coffee {
    override fun cost(): Int {
        return coffee.cost() + 70
    }

    override fun description(): String {
        return coffee.description() + ", ваниль"
    }
}
abstract class CoffeeDecorator(private val coffee: Coffee) : Coffee {
    abstract fun addedCost(): Int
    abstract fun addedDescription(): String

    override fun cost(): Int = coffee.cost() + addedCost()
    override fun description(): String = coffee.description() + addedDescription()
}

class MilkDecoratorV2(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun addedCost(): Int = 50
    override fun addedDescription(): String = ", молоко"
}

class SugarDecoratorV2(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun addedCost(): Int = 20
    override fun addedDescription(): String = ", сахар"
}

class VanillaDecoratorV2(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun addedCost(): Int = 70
    override fun addedDescription(): String = ", ваниль"
}