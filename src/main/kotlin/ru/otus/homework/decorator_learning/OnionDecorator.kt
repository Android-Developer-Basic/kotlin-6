package ru.otus.homework.decorator_learning

class OnionDecorator(pizza: BasicPizza) : PizzaDecorator(pizza) {

    override fun createPizza(): String {
        return super.createPizza() + ", с добавлением лука"
    }

}