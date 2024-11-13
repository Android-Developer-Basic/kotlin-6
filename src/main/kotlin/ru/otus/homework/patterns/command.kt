package ru.otus.homework.patterns

interface Command {
    fun execute()
}

class Run(private val action: () -> Unit) : Command {
    override fun execute() {
        action()
    }
}

class CommandExecutor {
    fun execute(command: Command) {
        command.execute()
    }
}

fun main() {
    val commandExecutor = CommandExecutor()
    commandExecutor.execute(Run { println("Hello") })
}