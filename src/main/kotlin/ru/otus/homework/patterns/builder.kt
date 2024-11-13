package ru.otus.homework.patterns

interface BurgerMaker {
    fun kneadTheDough(): BurgerMaker
    fun makeGroundMeat(meat: Meat): BurgerMaker
    fun bakeBun(): BurgerMaker
    fun fryBeef(doneness: Doneness): BurgerMaker
    fun assembleBurger(toppings: List<String>): Burger
}

class ClassicBurgerMaker: BurgerMaker {
    private lateinit var dough: String
    private lateinit var groundMeat: String
    private lateinit var bun: String
    private lateinit var patty: String

    override fun kneadTheDough(): BurgerMaker {
        println("Knead the dough")
        dough = "dough from wheat flour"
        return this
    }

    override fun makeGroundMeat(meat: Meat): BurgerMaker {
        println("Make ground meat")
        groundMeat = meat.toString()
        return this
    }

    override fun bakeBun(): BurgerMaker {
        println("Bake bun")
        bun = "flavorous bun from $dough"
        return this
    }

    override fun fryBeef(doneness: Doneness): BurgerMaker {
        println("Fry beef")
        patty = "$doneness patty from $groundMeat"
        return this
    }

    override fun assembleBurger(toppings: List<String>): Burger {
        println("Assemble burger")
        return Burger(bun, patty, toppings.joinToString(", "))
    }
}

class Burger(private val bun: String, private val patty: String, private val vegetables: String) {
    override fun toString(): String {
        return "Burger with $bun, $patty and $vegetables"
    }
}

sealed class Meat private constructor(private val meat: String){
    object Beef: Meat("beef")
    object Pork: Meat("pork")
    object Lamb: Meat("lamb")

    override fun toString(): String {
        return meat
    }
}

sealed class Doneness private constructor(private val doneness: String){
    object Raw: Doneness("raw")
    object Medium: Doneness("medium")
    object WellDone: Doneness("well done")

    override fun toString(): String {
        return doneness
    }
}

class ChiefBurgerMaker(private val burgerMaker: BurgerMaker) {
    fun makeBurger(): Burger {
        return burgerMaker
            .kneadTheDough()
            .makeGroundMeat(Meat.Beef)
            .bakeBun()
            .fryBeef(Doneness.Medium).assembleBurger(listOf("tomato", "onion", "cheese"))
    }
}


fun main() {
    val chief = ChiefBurgerMaker(ClassicBurgerMaker())
    println(chief.makeBurger())
}

