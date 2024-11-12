package ru.otus.homework.patterns

interface Pet {
    fun sound(): String
}

class Dog : Pet {
    override fun sound() = "Bark"
}

class DogWithClothes(private val decoratedDog: Pet) : Pet {
    override fun sound(): String {
        return decoratedDog.sound() + " in clothes"
    }
}

fun main() {
    val simpleDog = Dog()
    val dressedDog = DogWithClothes(simpleDog)

    println("Simple Dog: ${simpleDog.sound()}")
    println("Dressed Dog: ${dressedDog.sound()}")
}