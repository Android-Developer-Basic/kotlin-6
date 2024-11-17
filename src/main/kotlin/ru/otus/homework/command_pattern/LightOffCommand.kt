package ru.otus.homework.command_pattern

class LightOffCommand(private val light: Light) : SomeCommand {
    override fun execute() {
        light.turnOff()
    }

    override fun undo() {
        light.turnOn()
    }
}

