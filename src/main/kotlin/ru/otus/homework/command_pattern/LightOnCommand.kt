package ru.otus.homework.command_pattern

class LightOnCommand(private val light: Light) : SomeCommand {
    override fun execute() {
        light.turnOn()
    }

    override fun undo() {
        light.turnOff()
    }
}