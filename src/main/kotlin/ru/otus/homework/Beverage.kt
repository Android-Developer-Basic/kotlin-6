package ru.otus.homework

// Компонент
interface Beverage {
    fun cost(): Int
    fun name(): String
}

// Конкретный компонент
class Coffee:Beverage{
    override fun cost(): Int = 300

    override fun name(): String  = "Кофе"
}

// Декоратор

abstract class BeverageDecorator(private val beverage: Beverage):Beverage{
    override fun cost(): Int = beverage.cost()

    override fun name(): String = beverage.name()
}

class SugarDecorator(beverage: Beverage):BeverageDecorator(beverage){
    override fun cost(): Int {
        return super.cost() + 10
    }

    override fun name(): String {
        return super.name() + " + Сахар"
    }
}
class MilkDecorator(beverage: Beverage):BeverageDecorator(beverage){
    override fun cost(): Int {
        return super.cost() + 30
    }

    override fun name(): String {
        return super.name() + " + Молоко"
    }
}