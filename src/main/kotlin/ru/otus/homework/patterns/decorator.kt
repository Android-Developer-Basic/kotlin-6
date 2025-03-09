package ru.otus.homework.patterns

/**
 * https://en.wikipedia.org/wiki/Decorator_pattern
 */
fun main (){
    listOf(
        Soda(),
        Coffee(),
        Tea(),
        WithMilk(3.2F, Tea()),
        WithIceCubes(3, Soda()),
        WithSugar(2, WithMilk(2.0F,  Coffee())),
        WithSugar(3,  Coffee()))
        .forEach {
            println(it.getDescription() + " стоит ${it.getCost()} р")
        }
}

/**
 * Напитки
 */
interface Beverage {
    fun getDescription(): String
    fun getCost(): Float
}

class Coffee: Beverage {
    override fun getCost(): Float {
        return 100.0F
    }
    override fun getDescription(): String {
        return "Кофе"
    }
}

class Tea: Beverage {
    override fun getCost(): Float {
        return 50.0F
    }
    override fun getDescription(): String {
        return "Чай"
    }
}

class Soda: Beverage {
    override fun getCost(): Float {
        return 10.0F
    }
    override fun getDescription(): String {
        return "Газировка"
    }
}

/**
 * Компоненты (модификаторы)
 */
abstract class BeverageDecorator(private val b: Beverage) : Beverage {
    override fun getDescription(): String = b.getDescription()
    override fun getCost(): Float = b.getCost()
}

class WithSugar(private val spoon: Int, b: Beverage): BeverageDecorator(b) {
    override fun getCost(): Float {
        return super.getCost() + 3.0F * spoon
    }
    override fun getDescription(): String {
        return super.getDescription() + ", с $spoon ложками сахара"
    }
}

class WithMilk(private val percent: Float, b: Beverage): BeverageDecorator(b) {
    override fun getCost(): Float {
        return super.getCost() + 10.0F * percent
    }
    override fun getDescription(): String {
        return super.getDescription() + ", с $percent% молоком"
    }
}

class WithIceCubes(private val count: Int, b: Beverage): BeverageDecorator(b) {
    override fun getCost(): Float {
        return super.getCost() + 1.0F * count
    }
    override fun getDescription(): String {
        return super.getDescription() + ", с $count кубиками льда"
    }
}
