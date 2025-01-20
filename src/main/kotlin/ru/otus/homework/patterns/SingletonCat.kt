package ru.otus.homework.patterns

object SingletonCat {
    var mood: String = "Happy"
}

fun main() {
    val cat1 = SingletonCat
    val cat2 = SingletonCat

    cat1.mood = "Sleepy"

    println("cat1 mood: ${cat1.mood}")
    println("cat2 mood: ${cat2.mood}")
}
