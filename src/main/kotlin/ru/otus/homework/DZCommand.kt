package ru.otus.homework

interface Command {
    fun execute()
}

class LightOnCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOn()
    }
}

class LightOffCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOff()
    }
}

class Light {
    fun turnOn() {
        println("Light is ON")
    }

    fun turnOff() {
        println("Light is OFF")
    }
}

class RemoteControl(private var command: Command? = null) {
    fun setCommand(command: Command) {
        this.command = command
    }

    fun pressButton() {
        command?.execute()
    }
}

fun main() {
    val light = Light()
    val lightOn = LightOnCommand(light)
    val lightOff = LightOffCommand(light)

    val remote = RemoteControl()

    remote.setCommand(lightOn)
    remote.pressButton() // Output: Light is ON

    remote.setCommand(lightOff)
    remote.pressButton() // Output: Light is OFF
}
