package ru.otus.homework.patterns

object Menu  {
    private val menu: MutableMap< String, Int> = mutableMapOf(
        "iceCream" to 2,
        "chocolate" to 5,
        "berries" to 7,
        "service" to 1
    )

    fun getPositionCost(key : String): Int {
        return menu[key] ?: 0
    }

    fun addPositionToMenu(key: String, value: Int) {
        menu[key] = value
    }
}