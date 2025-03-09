package ru.otus.homework.patterns

/**
 *  https://en.wikipedia.org/wiki/Command_pattern
 */
fun main() {
    SimpleCommand(Receiver()).execute()
}


interface Command {
    fun execute()
}

class SimpleCommand(private val r: Receiver) : Command {
    override fun execute() = r.action()
}

class Receiver {
    fun action() {
        println("Receiver::action")
    }
}
