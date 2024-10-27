package ru.otus.homework.patterns

class MilkCocktail() {
    var base: String = ""
    var flavour: String = ""
    var cake: String = ""
    var frosting: String = ""

    override fun toString(): String {
        return "$base cocktail with $flavour flavour, having $cake with $flavour frosting on top"
    }

    companion object {
        fun construct(flavour: String, cake: String): MilkCocktail = MilkCocktail().apply {
            this.base = "Milk"
            this.flavour = flavour
            this.cake = cake
            this.frosting = flavour
        }
    }
}