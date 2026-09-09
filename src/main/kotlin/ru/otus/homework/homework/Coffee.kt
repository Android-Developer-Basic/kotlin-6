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
    private val MILK_COST = 50
    private val MILK_SUFFIX = ", молоко"
    override fun cost(): Int {
        return coffee.cost() + MILK_COST
    }

    override fun description(): String {
        return "${coffee.description()}$MILK_SUFFIX"
    }
}

class SugarDecorator(private val coffee: Coffee) : Coffee {
    private val SUGAR_COST = 20
    private val SUGAR_SUFFIX = ", сахар"
    override fun cost(): Int {
        return coffee.cost() + SUGAR_COST
    }

    override fun description(): String {
        return "${coffee.description()}$SUGAR_SUFFIX"
    }
}

class VanillaDecorator(private val coffee: Coffee) : Coffee {
    private val VANILLA_COST = 70
    private val VANILLA_SUFFIX = ", ваниль"
    override fun cost(): Int {
        return coffee.cost() + VANILLA_COST
    }

    override fun description(): String {
        return "${coffee.description()}$VANILLA_SUFFIX"
    }
}