package ru.otus.homework

// Продукт
data class Pizza(
    val name: String,
    val size: Int,
    val cheese: Boolean,
    val tomato: Boolean,
    val mushrooms: Boolean
)

// Интерфейс строителя
interface PizzaBuilder {
    fun setName(name: String):PizzaBuilder
    fun setSize(size: Int):PizzaBuilder
    fun addCheese():PizzaBuilder
    fun addTomato():PizzaBuilder
    fun addMushrooms():PizzaBuilder
    fun build(): Pizza
}

// Конкретный строитель (повар)

class ConcretePizzaBuilder: PizzaBuilder{

    private var name: String = "Мясная"
    private var size: Int = 32
    private var cheese: Boolean = false
    private var tomato: Boolean = false
    private var mushrooms: Boolean = false

    override fun setName(name: String): PizzaBuilder {
        this.name = name
        return this
    }

    override fun setSize(size: Int): PizzaBuilder {
        this.size = size
        return this
    }

    override fun addCheese(): PizzaBuilder {
        this.cheese = true
        return this
    }

    override fun addTomato(): PizzaBuilder {
        this.tomato = true
        return this
    }

    override fun addMushrooms(): PizzaBuilder {
        this.mushrooms = true
        return this
    }

    override fun build(): Pizza {
        return Pizza(name,size,cheese, tomato, mushrooms)
    }

}

// Директор

class PizzaDirector(private val builder: PizzaBuilder){
    fun makeMargheritaPizza(){
        builder.setName("Маргарита")
            .setSize(32)
            .addCheese()
            .build()
    }

    fun makePepperoniPizza(){
        builder.setName("Пепперони")
            .setSize(28)
            .addTomato()
            .addMushrooms()
            .build()
    }
}