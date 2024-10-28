package ru.otus.homework.homework

class Singleton private constructor() {

    companion object {
        val instance: Singleton by lazy {
            Singleton()
        }
    }

}

fun main() {
    val singleton = Singleton.instance
    val singleton2 = Singleton.instance
    println(singleton == singleton2)
}