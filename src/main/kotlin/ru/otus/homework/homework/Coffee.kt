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

abstract class DecoratorCoffee(private val coffee: Coffee): Coffee{
    override fun cost() = coffee.cost()
    override fun description() = coffee.description()
}

class MilkDecorator(private val coffee: Coffee) : DecoratorCoffee(coffee) {
    override fun cost() = super.cost() + 50
    override fun description() = super.description() + ", молоко"
}

class SugarDecorator(private val coffee: Coffee) : DecoratorCoffee(coffee) {
    override fun cost() = super.cost() + 20
    override fun description() = super.description() + ", сахар"
}

class VanillaDecorator(private val coffee: Coffee) : DecoratorCoffee(coffee) {
    override fun cost() = super.cost() + 70
    override fun description() = super.description() + ", ваниль"
}