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

// Абстрактный базовый класс для декораций
abstract class AbstractDecorator(protected val decoratedCoffee: Coffee) : Coffee {
    protected abstract val additionalCost: Int
    protected abstract val additionalDescription: String

    final override fun cost() = decoratedCoffee.cost() + additionalCost
    final override fun description() = "${decoratedCoffee.description()}, $additionalDescription"
}

class MilkDecorator(coffee: Coffee) : AbstractDecorator(coffee) {
    override val additionalCost = 50
    override val additionalDescription = "молоко"
}

class SugarDecorator(decoratedCoffee: Coffee) : AbstractDecorator(decoratedCoffee) {
    override val additionalCost = 20
    override val additionalDescription = "сахар"
}

class VanillaDecorator(decoratedCoffee: Coffee) : AbstractDecorator(decoratedCoffee) {
    override val additionalCost = 70
    override val additionalDescription = "ваниль"
}