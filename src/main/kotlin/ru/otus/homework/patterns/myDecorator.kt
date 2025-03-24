package ru.otus.homework.patterns

fun main()
{
    val pizza1 = TomatoPizza(ItalianPizza())    // итальянская пицца с томатами
    println("Название: ${pizza1.getName()}");
    println("Цена: ${pizza1.getCost()}");

    val pizza2 = CheesePizza(ItalianPizza())    // итальянская пицца с сыром
    println("Название: ${pizza2.getName()}");
    println("Цена: ${pizza2.getCost()}");

    val pizza3 = TomatoPizza(BulgerianPizza())
    val pizza4 = CheesePizza(pizza3)    // болгарская пицца с томатами и сыром
    println("Название: ${pizza4.getName()}")
    println("Цена: ${pizza4.getCost()}")
}

interface Pizza {
    fun getName() : String
    fun getCost() : Int
}

class ItalianPizza : Pizza {
    override fun getName() : String = "Итальянская пицца"
    override fun getCost() : Int = 10
}
class BulgerianPizza : Pizza {
    override fun getName() : String = "Болгарская пицца"
    override fun getCost() : Int = 8
}

abstract class PizzaDecorator (parPizza : Pizza) : Pizza {
    var pizza : Pizza = parPizza
}

class TomatoPizza(parPizza : Pizza) : PizzaDecorator(parPizza) {
    override fun getName(): String {
        return pizza.getName() + ", с томатами"
    }
    override fun getCost() : Int {
        return pizza.getCost() + 3
    }
}

class CheesePizza(parPizza : Pizza) : PizzaDecorator(parPizza) {
    override fun getName(): String {
        return pizza.getName() + ", с сыром"
    }
    override fun getCost() : Int {
        return pizza.getCost() + 5
    }
}
