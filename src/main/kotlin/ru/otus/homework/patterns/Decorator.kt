package ru.otus.homework.patterns

interface Decorator {
    fun decorate(): String
}

class Cake: Decorator {
    override fun decorate(): String = "Just a cake"
}

open class CakeDecorator(private var cake: Decorator): Decorator {
    override fun decorate(): String = cake.decorate()

}

class CreamDecorator(cake: Decorator): CakeDecorator(cake) {
    override fun decorate(): String = super.decorate() + " with cream"
}

class BerriesDecorator(cake: Decorator): CakeDecorator(cake) {
    override fun decorate(): String = super.decorate() + " and berries"
}

class CaramelDecorator(cake: Decorator): CakeDecorator(cake) {
    override fun decorate(): String = super.decorate() + " and caramel"

}
fun main() {
    val cakeAfterDecoration = CaramelDecorator(
        BerriesDecorator(
            CreamDecorator(
                Cake()
            )
        )
    ).decorate()

    print(cakeAfterDecoration)
}
