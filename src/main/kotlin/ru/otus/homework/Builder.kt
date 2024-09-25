package ru.otus.homework

class Product {

    var part1: String? = null
    var part2: String? = null
    var part3: String? = null

    class ProductBuilder {
        private var part1: String? = null
        private var part2: String? = null
        private var part3: String? = null

        fun part1(part1: String): ProductBuilder {
            this.part1 = part1
            return this
        }

        fun part2(part2: String): ProductBuilder {
            this.part2 = part2
            return this
        }

        fun part3(part3: String): ProductBuilder {
            this.part3 = part3
            return this
        }

        fun build(): Product {
            val result = Product()

            result.part1 = part1
            result.part2 = part2
            result.part3 = part3

            return result
        }
    }

    companion object {
        fun builder(): ProductBuilder = ProductBuilder()
    }
}

fun main() {
    val product = Product.builder()
        .part1("Часть 1")
        .part2("Часть 2")
        .part3("Часть 3")
        .build()

    println(product.part1)
    println(product.part2)
    println(product.part3)
}
