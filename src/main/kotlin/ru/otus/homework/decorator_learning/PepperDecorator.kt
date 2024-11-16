package ru.otus.homework.decorator_learning

class PepperDecorator(pizza: BasicPizza) : PizzaDecorator(pizza) {

    override fun createPizza(): String {
        return super.createPizza() + ", с добавлением перца"
    }

}