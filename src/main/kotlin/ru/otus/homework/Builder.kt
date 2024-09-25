package ru.otus.homework

class Product {
    var part1: String? = null
    var part2: String? = null
    var part3: String? = null
}

class Builder {
    private val product = Product()

    fun setPart1(part1: String): Builder {
        product.part1 = part1
        return this
    }

    fun setPart2(part2: String): Builder {
        product.part2 = part2
        return this
    }

    fun setPart3(part3: String): Builder {
        product.part3 = part3
        return this
    }

    fun build(): Product {
        return product
    }
}

fun main() {
    val product = Builder().setPart1("Часть 1")
        .setPart2("Часть 2")
        .setPart3("Часть 3")
        .build()

    println(product.part1)
    println(product.part2)
    println(product.part3)
}
