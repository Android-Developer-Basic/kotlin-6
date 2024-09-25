package ru.otus.homework

interface Command {
    fun execute()
}

class TurnOnLightCommand : Command {
    override fun execute() {
        println("Свет включен")
    }
}

class TurnOffLightCommand : Command {
    override fun execute() {
        println("Свет выключен")
    }
}

class RemoteControl {
    private val commands: MutableList<Command> = mutableListOf()

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun executeCommand(index: Int) {
        if (index in commands.indices) {
            commands[index].execute()
        } else {
            println("Неверный индекс")
        }
    }
}

fun main() {
    val remoteControl = RemoteControl()
    remoteControl.addCommand(TurnOnLightCommand())
    remoteControl.addCommand(TurnOffLightCommand())

    remoteControl.executeCommand(0)
    remoteControl.executeCommand(1)
}
