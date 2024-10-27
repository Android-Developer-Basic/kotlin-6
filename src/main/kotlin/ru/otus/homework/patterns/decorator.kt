package ru.otus.homework.patterns

class IceCream {
    var cost = 0
    var ingredients: MutableList<String> = mutableListOf()
    init {
        println("Preparing")
        cost = Menu.getPositionCost("iceCream")
        ingredients.add("iceCream")
    }

}

class IceCreamDecorator(iceCream: IceCream, topping: String) {
    private var cost = 0
    private var ingredients: MutableList<String>

    init {
        cost = iceCream.cost + Menu.getPositionCost(topping)
        ingredients = iceCream.ingredients
        ingredients.add(topping)
    }

    fun serve() {
        cost += Menu.getPositionCost("service")
        println("Your dessert, cost is $cost, ingredients: ${ingredients.joinToString(", ")}")
    }
}

fun main() {
    val order: List<IceCreamDecorator> =  listOf(IceCreamDecorator(IceCream(), "chocolate"), IceCreamDecorator(IceCream(), "berries"))
    order.forEach { it.serve() }

    val waiter = Waiter()
    waiter.setInstruction(ServeIceCreamCommand("berries"), "berryIceCream")
    waiter.executeInstruction("berryIceCream")

    val milkCocktail = MilkCocktail.construct("cherry", "lemon")
    println(milkCocktail)
}