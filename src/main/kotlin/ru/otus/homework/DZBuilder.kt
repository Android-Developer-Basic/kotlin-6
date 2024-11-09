package ru.otus.homework

class Pizza private constructor(
    val size: String,
    val cheese: String? = null,
    val pepperoni: Boolean = false,
    val mushrooms: Boolean = false
) {

    data class Builder(
        var size: String? = null,
        var cheese: String? = null,
        var pepperoni: Boolean = false,
        var mushrooms: Boolean = false
    ) {

        fun size(size: String) = apply { this.size = size }
        fun cheese(cheese: String) = apply { this.cheese = cheese }
        fun pepperoni() = apply { this.pepperoni = true }
        fun mushrooms() = apply { this.mushrooms = true }

        fun build(): Pizza {
            requireNotNull(size) { "Size must be set" }
            return Pizza(size!!, cheese, pepperoni, mushrooms)
        }
    }
}

fun main() {
    val pizza = Pizza.Builder()
        .size("large")
        .cheese("mozzarella")
        .pepperoni()
        .mushrooms()
        .build()

    println(pizza)
}
