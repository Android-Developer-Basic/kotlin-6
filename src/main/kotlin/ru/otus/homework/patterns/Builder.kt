package ru.otus.homework.patterns

fun main() {
    val soupCoked = SoupBuilder()
        .addWater()
        .addSpices()
        .addPotato()
        .build()
    println(soupCoked.soupComponents.toString())
}

interface CookSoup {
    fun addWater(): SoupBuilder
    fun addSpices(): SoupBuilder
    fun addPotato(): SoupBuilder
    fun build(): Soup
}

class SoupBuilder : CookSoup {
    private val soup = Soup()

    override fun addWater(): SoupBuilder {
        soup.addComponent("water")
        return this
    }

    override fun addSpices(): SoupBuilder {
        soup.addComponent("spices")
        return this
    }

    override fun addPotato(): SoupBuilder {
        soup.addComponent("potato")
        return this
    }

    override fun build(): Soup {
        return soup
    }
}

class Soup {
    val soupComponents: MutableList<String> = mutableListOf()

    fun addComponent(component: String) {
        soupComponents.add(component)
    }
}