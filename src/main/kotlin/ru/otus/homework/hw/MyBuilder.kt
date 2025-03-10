package ru.otus.homework.hw

class Recipe private constructor(val ingredients: MutableMap<String, Int>) {

    data class Builder(var ingredients: MutableMap<String, Int>? = null) {

        fun addIngredient(input: Pair<String, Int>): Builder {
            if (ingredients == null) ingredients  = mutableMapOf()
            ingredients!!.put(input.first, input.second)
            return Builder(ingredients)
        }

        fun build() = ingredients?.let { Recipe(it) }

    }

    override fun toString(): String {
        return "Recipe: ${ingredients}"
    }
}

fun main() {
    val recipe = Recipe.Builder()
        .addIngredient("flour" to 200)
        .addIngredient("water" to 100)
        .addIngredient("salt" to 1)
        .build()

    println(recipe)
}