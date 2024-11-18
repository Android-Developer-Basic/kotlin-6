package ru.otus.homework.patterns

class Pizza private constructor(
    val size: Size,
    val doughType: DoughType,
    val sauce: Sauce?,
    val toppings: List<Topping>
) {
    // Вложенный класс Builder
    class Builder {
        private lateinit var size: Size
        private lateinit var doughType: DoughType
        private var sauce: Sauce? = null
        private val toppings = mutableListOf<Topping>()

        fun size(size: Size) = apply { this.size = size }
        fun doughType(doughType: DoughType) = apply { this.doughType = doughType }
        fun sauce(sauce: Sauce?) = apply { this.sauce = sauce }
        fun addTopping(topping: Topping) = apply { toppings.add(topping) }

        fun build(): Pizza {
            return Pizza(size, doughType, sauce, toppings)
        }
    }
}

// Пример перечислений для параметров
enum class Size {
    SMALL, MEDIUM, LARGE
}

enum class DoughType {
    THIN, THICK
}

enum class Sauce {
    TOMATO, BBQ, PESTO
}

enum class Topping {
    CHEESE, PEPPERONI, MUSHROOMS, ONIONS
}

// Использование паттерна Builder
fun main() {
    val pizza = Pizza.Builder()
        .size(Size.LARGE)
        .doughType(DoughType.THICK)
        .sauce(Sauce.TOMATO)
        .addTopping(Topping.CHEESE)
        .addTopping(Topping.PEPPERONI)
        .build()

    println("Pizza created: ${pizza.size}, ${pizza.doughType}, ${pizza.sauce}, ${pizza.toppings}")
}