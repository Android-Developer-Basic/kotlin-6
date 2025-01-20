package ru.otus.homework.patterns

interface Olivie {
    fun addIngredients(): String
}

class BasicOlivie : Olivie {
    override fun addIngredients() : String = "Potato, carrot, eggs, and cucumber have been added."
}

abstract class OlivieDecorator(private val decoratedOlivie: Olivie) : Olivie {
    override fun addIngredients(): String = decoratedOlivie.addIngredients()
}
class AddMayoDecorator (olivie: Olivie) : OlivieDecorator(olivie) {
    override fun addIngredients(): String = super.addIngredients() + " Mayo added."
}
class AddSmetanaDecorator (olivie: Olivie) : OlivieDecorator(olivie) {
    override fun addIngredients(): String = super.addIngredients() + " Smetana added."
}

class AddChickenDecorator (olivie: Olivie) : OlivieDecorator(olivie) {
    override fun addIngredients(): String = super.addIngredients() + " Chicken added."
}

class AddDoktorskayaDecorator (olivie: Olivie) : OlivieDecorator(olivie) {
    override fun addIngredients(): String = super.addIngredients() + " Doktorskaya added."
}

fun main() {
    val olivieBase = BasicOlivie()
    println(olivieBase.addIngredients())
    val olivieWithSmetana = AddSmetanaDecorator(olivieBase)
    println(olivieWithSmetana.addIngredients())
    val olivieWithMayo = AddMayoDecorator(olivieBase)
    println(olivieWithMayo.addIngredients())
    val lowCalorieOlivie = AddSmetanaDecorator(AddChickenDecorator(olivieBase))
    println(lowCalorieOlivie.addIngredients())
    val fullPackOlivie = AddMayoDecorator(AddDoktorskayaDecorator(olivieBase))
    println(fullPackOlivie.addIngredients())
}
