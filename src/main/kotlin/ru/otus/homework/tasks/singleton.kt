package ru.otus.homework.tasks

import kotlin.random.Random

fun main() {
    checkSingletonObjects()
    getJokes()
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

interface PupaLupaJoke {
    fun getJoke(): String
}

abstract class PupaLupaJokeDecorator(protected val joke: PupaLupaJoke) : PupaLupaJoke {
    override fun getJoke(): String = joke.getJoke()
}

val SalaryJoke = object : PupaLupaJoke {
    override fun getJoke(): String =
        "Пошли как-то два друга, Лупа и Пупа, получать зарплату. В бухгалтерии все перепутали. Лупа получил за Пупу, а Пупа за Лупу"
}

class ExplanationDecorator(joke: PupaLupaJoke) : PupaLupaJokeDecorator(joke) {
    override fun getJoke(): String = "${joke.getJoke()} ну типо за Лупу = залупу"
}

class LaughDecorator(joke: PupaLupaJoke) : PupaLupaJokeDecorator(joke) {
    override fun getJoke(): String = "${joke.getJoke()} \nахахавхахвахвахахв"
}


fun getJokes() {
    println(SalaryJoke.getJoke())
    println(ExplanationDecorator(SalaryJoke).getJoke())
    println(LaughDecorator(ExplanationDecorator(SalaryJoke)).getJoke())

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
