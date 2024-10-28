package ru.otus.homework.patterns

class Order(var part1: String, var part2: String) {
    class Builder()
    {
        var part1: String = ""
        var part2: String = ""

        fun build(): Order {
            return Order(part1, part2)
        }
    }

    override fun toString(): String = "part1 = '$part1', part2 = '$part2'"
}

fun main() {
    val builder = Order.Builder().apply {
        part1 = "common"
        part2 = "custom" }

    builder.part2 = "part 2 for order 1"
    val order1 = builder.build()

    builder.part2 = "part 2 for order 2"
    val order2 = builder.build()

    builder.part2 = "part 2 for order 3"
    val order3 = builder.build()

    println("order1: ${order1.toString()}")
    println("order2: ${order2.toString()}")
    println("order3: ${order3.toString()}")
}