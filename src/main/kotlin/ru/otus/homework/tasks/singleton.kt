package ru.otus.homework.tasks

import kotlin.random.Random

fun main() {
    checkSingletonObjects()
    getNumberToString()
    useCommand()

}

fun checkSingletonObjects() {
    val getNumber = RandomNumberProvider.number
    val getSecondNumber = RandomNumberProvider.number
    println(getNumber.hashCode() == getSecondNumber.hashCode())
    println(getNumber == getSecondNumber)
    println(getNumber)
}

object RandomNumberProvider {
    val number: Int by lazy {
        return@lazy Random.nextInt()
    }
}

class DecoratorToString {
    val getStringNumber: String = RandomNumberProvider.number.toString()
}

fun getNumberToString() {
    println(DecoratorToString().getStringNumber)
}

abstract class Command {
    companion object {
        fun increment(val1: Int, val2: Int) = val1 + val2
    }

    open fun printJoke() =
        println("Если бы в Африке были москитные сетки, мы бы спасли МИЛЛИОНЫ комаров от СПИДа")
}

class CommandUser : Command() {
    override fun printJoke() {
        println("Извиняюсь перед всеми Африканцами")
        super.printJoke()
    }
}

fun useCommand() {
    val command = CommandUser()
    command.printJoke()
    Command.increment(2323, 3210)
}
