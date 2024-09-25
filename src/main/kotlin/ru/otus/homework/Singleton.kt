package ru.otus.homework

object Logger {
    fun log(message: String) {
        println("Лог: $message")
    }
}

fun main() {
    Logger.log("Привет из main!")
}
