package ru.otus.homework.patterns

interface Command {
    fun execute()
}

class ServeIceCreamCommand(topping: String): Command {
    private val iceCream = IceCreamDecorator(IceCream(), topping)
    override fun execute() {
        iceCream.serve()
    }
}

class Waiter {
    val instructions: MutableMap< String, Command> = mutableMapOf()
    fun setInstruction(command: Command, name: String) {
        instructions[name] = command
    }

    fun executeInstruction(name: String) {
        instructions[name]?.execute()
    }
}